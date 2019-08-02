package com.conv.aayojok.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.conv.aayojok.Models.CurrentUser
import com.conv.aayojok.Activity.EditProfileActivity
import com.conv.aayojok.R
import kotlinx.android.synthetic.main.profile.view.*

class AdminProfileFragment: Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.profile,null)

        Glide.with(this).load(CurrentUser.photo).apply(RequestOptions.overrideOf(250,200))
            .error(Glide.with(this).load(R.drawable.blank_pro_pic)).into(view.proPic)

        view.nameField.text = CurrentUser.name
        view.emailField.text = CurrentUser.email
        view.gender.text = CurrentUser.gender
        view.ageField.text = CurrentUser.age
        view.occ.text = CurrentUser.occupation
        view.nat.text = CurrentUser.nationality
        view.insField.text = CurrentUser.institution
        view.phoneField.text = CurrentUser.phone

        view.editProfile.setOnClickListener {
            var i = Intent(this@AdminProfileFragment.context, EditProfileActivity::class.java)
            i.putExtra("from", "admin")
            startActivity(i)
        }
        return view
    }
}