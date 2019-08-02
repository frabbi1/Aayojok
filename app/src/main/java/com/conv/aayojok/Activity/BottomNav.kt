package com.conv.aayojok.Activity

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import com.conv.aayojok.Fragments.AdminProfileFragment
import com.conv.aayojok.Fragments.MyEventFragment
import com.conv.aayojok.Fragments.RequestEventFragment
import com.conv.aayojok.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class BottomNav : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when {
            item.itemId == R.id.navigation_myEvent -> return loadFragment(MyEventFragment())
            item.itemId == R.id.navigation_newEvent -> {

                return loadFragment(RequestEventFragment())
            }
            item.itemId == R.id.navigation_profile -> {

                return loadFragment(AdminProfileFragment())
            }
            item.itemId == R.id.navigation_logOut -> {

                signout()

                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return false
            }
            else -> return loadFragment(MyEventFragment())
        }

    }

    private fun signout(){

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        var mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mGoogleSignInClient!!.signOut()
            .addOnCompleteListener(this) {
                // ...
                Toast.makeText(this, "You are signed Out", Toast.LENGTH_SHORT).show()
            }

    }


    private fun loadFragment(fragment: Fragment):Boolean{

        if(fragment!=null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()

            return true
        }
        return false


    }

    override fun onBackPressed() {
        finish()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_nav)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)


        navView.setOnNavigationItemSelectedListener(this)
        loadFragment(MyEventFragment())



    }
}
