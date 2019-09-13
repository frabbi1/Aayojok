package com.conv.aayojok.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit_profile.*
import com.conv.aayojok.Models.CurrentUser
import com.conv.aayojok.Models.User
import com.conv.aayojok.R
import com.conv.aayojok.services.BackEndService
import com.conv.aayojok.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Response


class EditProfileActivity : AppCompatActivity() {

    lateinit var toolbar: ActionBar
    private var name = ""
    private var email = ""
    private var id = ""
    private var photo = ""
    private var age = ""
    private var occupation = ""
    private var institution = ""
    private var nationality = ""
    private var gender = ""
    private var phone = ""

    private var from = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        toolbar = supportActionBar!!
        toolbar.title = "Edit Profile"
        var intent = intent
        from = intent.getStringExtra("from")


        nameF.setText(CurrentUser.name)
        ageF.setText(CurrentUser.age)
        phoneF.setText(CurrentUser.phone)
        occupationF.setText(CurrentUser.occupation)
        insF.setText(CurrentUser.institution)
        nationalityF.setText(CurrentUser.nationality)


        save.setOnClickListener {
            var done = saveinfo()
            if(done){

                val service = ServiceBuilder.buildService(BackEndService::class.java)
                var user = User(CurrentUser.id, CurrentUser.name, CurrentUser.email, CurrentUser.age, CurrentUser.gender, occupation, institution, phone, nationality, CurrentUser.photo)
                val requestCall = service.updateParticipant(CurrentUser.id,user)

                requestCall.enqueue(object : retrofit2.Callback<User>{
                    override fun onResponse(call: Call<User>, response: Response<User>){
                        if(response.isSuccessful){
                            var temp = response.body()


                        }else{

                            Toast.makeText(this@EditProfileActivity, "Failed to edit information", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        finish()
                        Toast.makeText(this@EditProfileActivity, "Information edit failed", Toast.LENGTH_SHORT)
                            .show()

                    }
                })

                Toast.makeText(this, "Profile Updated", Toast.LENGTH_LONG).show()
                if(from == "admin") {
                    var i = Intent(this, BottomNav::class.java)

                    startActivity(i)
                }
                else{
                    var i = Intent(this, Profile::class.java)

                    startActivity(i)
                }
                //finish()

            }

        }
    }

    private fun saveinfo() : Boolean{
        institution = insF.text.toString()
        occupation = occupationF.text.toString()
        phone = phoneF.text.toString()
        name = nameF.text.toString()
        age = ageF.text.toString()
        nationality = nationalityF.text.toString()



        //var genderButton = findViewById<RadioButton>(radiogrp.checkedRadioButtonId)
        var genderButton: Int = radiogrp.checkedRadioButtonId


        CurrentUser.name = name
        CurrentUser.age = age
        CurrentUser.phone = phone

        CurrentUser.institution = institution
        CurrentUser.occupation = occupation
        CurrentUser.nationality = nationality



        //check if the EditText have values or not
        if(name.trim().isEmpty() || age.trim().isEmpty()
            || genderButton==-1 || phone.trim().isEmpty()){


            Toast.makeText(this, "Fill up the required field(s)", Toast.LENGTH_LONG).show()
            return false
        }
        CurrentUser.gender = findViewById<RadioButton>(genderButton).text.toString()
        return true

    }

}
