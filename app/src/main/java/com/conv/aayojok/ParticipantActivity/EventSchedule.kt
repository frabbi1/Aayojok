package com.conv.aayojok.ParticipantActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.conv.aayojok.Models.CurrentEvent
import com.conv.aayojok.R
import com.conv.aayojok.services.BackEndService
import com.conv.aayojok.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_event_schedule.*
import retrofit2.Call
import retrofit2.Response

class EventSchedule : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_schedule)

        val service = ServiceBuilder.buildService(BackEndService::class.java)
        var requestCall = service.getSchedule(CurrentEvent.id)

        requestCall.enqueue(object : retrofit2.Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>){
                if(response.isSuccessful){
                    var fl = response.body()!!
                    txv_shed.text = fl

                }else{
                    Toast.makeText(this@EventSchedule, "Failed to retrieve details ", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                //finish()
                Toast.makeText(this@EventSchedule, "Failed to retrieve details. Check Internet Connection", Toast.LENGTH_SHORT)
                    .show()

                var i  = Intent(this@EventSchedule, ParticipantMainActivity::class.java)
                startActivity(i)

            }
        })





    }
}
