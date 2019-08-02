package com.conv.aayojok.ParticipantActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.conv.aayojok.Models.CurrentEvent
import com.conv.aayojok.R
import kotlinx.android.synthetic.main.activity_event_details_participant.*

class EventDetailsParticipant : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details_participant)

        event_name.text = CurrentEvent.name
        event_des.text = CurrentEvent.description
        event_loc.text = CurrentEvent.location
        event_sdate.text = CurrentEvent.startDate
        event_edate.text = CurrentEvent.endDate

        btn_loc.setOnClickListener {
            var i = Intent(this, LocationActivity::class.java)
            startActivity(i)
        }
    }
}
