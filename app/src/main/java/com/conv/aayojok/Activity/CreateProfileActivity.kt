package com.conv.aayojok.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import android.widget.RadioButton
import android.widget.Toast
import com.conv.aayojok.Models.CurrentUser
import com.conv.aayojok.Models.User
import com.conv.aayojok.R
import com.conv.aayojok.services.BackEndService
import com.conv.aayojok.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_edit_profile.*
import retrofit2.Call
import retrofit2.Response

class CreateProfileActivity : AppCompatActivity() {
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        toolbar = supportActionBar!!
        toolbar.title = "Create Profile"

        nameF.setText(CurrentUser.name)

        save.setOnClickListener {
            if(saveinfo()){
                Toast.makeText(this, "Profile Updated", Toast.LENGTH_LONG).show()
                var i = Intent(this, RoleSelector::class.java)
                startActivity(i)
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

        var genderButton = findViewById<RadioButton>(radiogrp.checkedRadioButtonId)
        // gender = genderButton.text.toString()

        //check if the EditText have values or not
        if(name.trim().isEmpty() || age.trim().isEmpty()
            || !genderButton.isChecked || phone.trim().isEmpty()){


            Toast.makeText(this, "Fill up the required field(s)", Toast.LENGTH_LONG).show()
            return false
        }
        gender = genderButton.text.toString()

        val service = ServiceBuilder.buildService(BackEndService::class.java)
        var newPaticipant = User(CurrentUser.id, name, CurrentUser.email, age, gender, occupation, institution, phone, nationality, CurrentUser.photo)
        val requestCall = service.addParticipant(newPaticipant)


        requestCall.enqueue(object : retrofit2.Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>){
                if(response.isSuccessful){
                    var temp = response.body()!!
                    /*val intent = Intent(this@InitialEditProfile, Navigation::class.java)
//
                    intent.putExtra("id",temp?.id)
//

                    startActivity(intent)*/
                    CurrentUser.id = temp.id
                    CurrentUser.name = temp.name
                    CurrentUser.email = temp.email
                    CurrentUser.gender = temp.gender
                    CurrentUser.occupation = temp.occupation!!
                    CurrentUser.institution = temp.institution!!
                    CurrentUser.phone = temp.phone
                    CurrentUser.nationality = temp.nationality!!
                    CurrentUser.photo = temp.photo!!
                    CurrentUser.age = temp.age




                }else{

                    Toast.makeText(this@CreateProfileActivity, "Failed to save information", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                finish()
                //Toast.makeText(this@InitialEditProfile, "Information save failed", Toast.LENGTH_SHORT)
                //   .show()

            }
        })


        return true

    }
}
