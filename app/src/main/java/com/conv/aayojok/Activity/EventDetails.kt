package com.conv.aayojok.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.conv.aayojok.R
import kotlinx.android.synthetic.main.activity_event_details.*

class EventDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)

        btn_change_details.setOnClickListener {
            var i = Intent(this, ChangeDetailsActivity::class.java)
            startActivity(i)
        }
    }
}
