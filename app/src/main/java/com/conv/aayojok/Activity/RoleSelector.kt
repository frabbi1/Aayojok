package com.conv.aayojok.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.conv.aayojok.ParticipantActivity.ParticipantMainActivity
import com.conv.aayojok.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.activity_role_selector.*

class RoleSelector : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_role_selector)


       /* btn_admin.setOnClickListener {
            var i = Intent(this, BottomNav::class.java)
            startActivity(i)
        }*/
        btn_participant.setOnClickListener {
            var i = Intent(this,ParticipantMainActivity::class.java)
            startActivity(i)
        }
        btn_RP.setOnClickListener {
            //var i = Intent(this,SideNav::class.java)
            //startActivity(i)
        }
        log_out.setOnClickListener {
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
}
