package com.conv.aayojok.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.conv.aayojok.Models.CurrentUser
import com.conv.aayojok.ParticipantActivity.ParticipantMainActivity
import com.conv.aayojok.R
import kotlinx.android.synthetic.main.profile.*
import kotlinx.android.synthetic.main.profile.view.*

class Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

                Glide.with(this).load(CurrentUser.photo).apply(RequestOptions.overrideOf(250,200))
            .error(Glide.with(this).load(R.drawable.blank_pro_pic)).into(proPic)

                nameField.text = CurrentUser.name
                emailField.text = CurrentUser.email
                gender.text = CurrentUser.gender
                ageField.text = CurrentUser.age
                occ.text = CurrentUser.occupation
                nat.text = CurrentUser.nationality
                insField.text = CurrentUser.institution
                phoneField.text = CurrentUser.phone

                editProfile.setOnClickListener {
                    var i = Intent(this, EditProfileActivity::class.java)
                    i.putExtra("from", "participant")
                     startActivity(i)
                }

    }

    override fun onBackPressed() {
        var i = Intent(this, ParticipantMainActivity::class.java)
        startActivity(i)
    }
}
