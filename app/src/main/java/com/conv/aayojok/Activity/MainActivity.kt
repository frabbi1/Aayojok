package com.conv.aayojok.Activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.conv.aayojok.Models.CurrentUser
import com.conv.aayojok.Models.User
import com.conv.aayojok.ParticipantActivity.ParticipantMainActivity
import com.conv.aayojok.R
import com.conv.aayojok.services.BackEndService
import com.conv.aayojok.services.ServiceBuilder
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

import java.io.Serializable

class MainActivity : AppCompatActivity(),Serializable {

    val RC_SIGN_IN = 9001
    private var count: Int = 0
    var mGoogleSignInClient: GoogleSignInClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()


        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        btn_login.setOnClickListener {

           var signInIntent : Intent = mGoogleSignInClient!!.signInIntent
            startActivityForResult(signInIntent,RC_SIGN_IN)
            //var i = Intent(this,CreateProfileActivity::class.java)
            //startActivity(i)
        }


    }

    override fun onBackPressed() {


        val alertDialog = AlertDialog.Builder(this)
            //set icon
            .setIcon(android.R.drawable.ic_dialog_alert)
            //set title
            .setTitle("Exit Application?")
            //set message

            //set positive button
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, i ->
                moveTaskToBack(true)
                android.os.Process.killProcess(android.os.Process.myPid())
                System.exit(1)
            })
            //set negative button
            .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->

            })
            .show()
    }
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {

            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)


            updateUI(account)
        } catch (e: ApiException) {
            //Toast.makeText(this, "signInResult:failed code=" + e.getStatusCode(),Toast.LENGTH_SHORT).show()

            updateUI(null)
        }

    }
    private fun updateUI(account: GoogleSignInAccount?) {
        if(account==null){
            //Toast.makeText(this, "Log in failed", Toast.LENGTH_LONG).show()

        }

        else{
            account?.let {
                var name :String = account.displayName.toString()
                var email = account.email.toString()
                var id = account.id.toString()
                var photo = account.photoUrl.toString()




                //Log.i("AuthenticationActivity", name)
                Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show()
                isNewUser(id,name,email,photo)


            }
        }


    }
    private fun isNewUser(id:String,name:String, email:String, photo:String){
        val service = ServiceBuilder.buildService(BackEndService::class.java)
        val requestCall = service.checkNewUser(id)
        var value:String

        requestCall.enqueue(object : retrofit2.Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>){
                if(response.isSuccessful){
                    var temp = response.body()
                    value = temp?.id.toString()

                    if(value == "0"){


                        CurrentUser.id = id
                        CurrentUser.email = email
                        CurrentUser.name = name
                        CurrentUser.photo = photo

                        var i = Intent(this@MainActivity, CreateProfileActivity::class.java)
                        startActivity(i)

                    }else{
                        CurrentUser.id = id
                        CurrentUser.email = email
                        CurrentUser.name = temp!!.name.toString()
                        CurrentUser.photo = photo
                        CurrentUser.age = temp?.age.toString()
                        CurrentUser.phone = temp?.phone.toString()
                        CurrentUser.institution = temp?.institution.toString()
                        CurrentUser.nationality = temp?.nationality.toString()
                        CurrentUser.occupation = temp?.occupation.toString()
                        CurrentUser.gender = temp?.gender.toString()
                        val intent = Intent(this@MainActivity, ParticipantMainActivity::class.java)
                        startActivity(intent)

                    }


                }else{
                    Toast.makeText(this@MainActivity, "Failed to retrieve details", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                finish()
                Toast.makeText(this@MainActivity, "Failed to retrieve details :( $t", Toast.LENGTH_SHORT)
                    .show()


            }
        })

    }
}
