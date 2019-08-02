package com.conv.aayojok.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.conv.aayojok.R
import kotlinx.android.synthetic.main.activity_event_menu_admin.*


class EventMenuAdmin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_menu_admin)

        cv_e_details.setOnClickListener {
            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
            var i = Intent(this, EventDetails::class.java)
            startActivity(i)
        }
        cv_not.setOnClickListener {


        }
        cv_upload.setOnClickListener {

        }
        cv_photo.setOnClickListener {

        }
        cv_file.setOnClickListener {

        }
        cv_near_by_place.setOnClickListener {

        }
    }
}
