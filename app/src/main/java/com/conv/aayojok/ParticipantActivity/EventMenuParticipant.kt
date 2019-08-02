package com.conv.aayojok.ParticipantActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.conv.aayojok.R
import kotlinx.android.synthetic.main.activity_event_menu_participant.*
import kotlinx.android.synthetic.main.activity_event_menu_participant.cv_e_details
import kotlinx.android.synthetic.main.activity_event_menu_participant.cv_file
import kotlinx.android.synthetic.main.activity_event_menu_participant.cv_photo

class EventMenuParticipant : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_menu_participant)

        cv_e_details.setOnClickListener {
            var i = Intent(this, EventDetailsParticipant::class.java)
            startActivity(i)
        }
        cv_loc.setOnClickListener {
            var i = Intent(this, LocationActivity::class.java)
            startActivity(i)
        }
        cv_file.setOnClickListener {
            var i = Intent(this, EventFiles::class.java)
            startActivity(i)
        }

        cv_photo.setOnClickListener {
            var i = Intent(this, PhotoGallery::class.java)
            startActivity(i)
        }
        cv_notification.setOnClickListener {

        }

        cv_participant_list.setOnClickListener {
            var i = Intent(this, ParticipantList::class.java)
            startActivity(i)
        }


        /*cv_rp.setOnClickListener {

        }*/
        cv_event_near_by_place.setOnClickListener {


            var i = Intent(this, NearbyPlaceList::class.java)
            startActivity(i)
        }
        btn_out.setOnClickListener {
            var i = Intent(this, EventListParticipant::class.java)
            startActivity(i)
        }





    }
}
