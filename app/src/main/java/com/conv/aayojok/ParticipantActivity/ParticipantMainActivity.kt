package com.conv.aayojok.ParticipantActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.conv.aayojok.Activity.MainActivity
import com.conv.aayojok.Activity.Profile
import com.conv.aayojok.Adapter.MyEventAdapter
import com.conv.aayojok.Models.CurrentUser
import com.conv.aayojok.Models.Event
import com.conv.aayojok.Models.EventSupplier
import com.conv.aayojok.R
import com.conv.aayojok.services.BackEndService
import com.conv.aayojok.services.ServiceBuilder
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.activity_participant_main.*
import kotlinx.android.synthetic.main.fragment_my_event.*
import retrofit2.Call
import retrofit2.Response

class ParticipantMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_participant_main)




        cv_profile.setOnClickListener {
            var i = Intent(this,Profile::class.java)
            startActivity(i)
        }
        cv_join_event.setOnClickListener {
            var i = Intent(this,JoinNewEvent::class.java)
            startActivity(i)
        }
        cv_p_all_event.setOnClickListener {
            var i = Intent(this,EventListParticipant::class.java)
            startActivity(i)
        }

        log_out_btn.setOnClickListener {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
            var mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
            mGoogleSignInClient!!.signOut()
                .addOnCompleteListener(this) {
                    // ...
                    Toast.makeText(this, "You are signed Out", Toast.LENGTH_SHORT).show()
                }

            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
    override fun onBackPressed() {
        var i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}
