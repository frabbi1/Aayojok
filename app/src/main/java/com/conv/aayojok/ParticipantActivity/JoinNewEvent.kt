package com.conv.aayojok.ParticipantActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.conv.aayojok.Models.CurrentEvent
import com.conv.aayojok.Models.CurrentUser
import com.conv.aayojok.Models.Event
import com.conv.aayojok.R
import com.conv.aayojok.services.BackEndService
import com.conv.aayojok.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_join_new_event.*
import retrofit2.Call
import retrofit2.Response

class JoinNewEvent : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_new_event)

        btnJoin.setOnClickListener {
            var id = eventID.text.toString()

            var code = eventCode.text.toString()

            if (id.trim().isEmpty() && code.trim().isEmpty()) {
                Toast.makeText(this@JoinNewEvent, "Empty Fields", Toast.LENGTH_SHORT).show()

            } else {
                val service = ServiceBuilder.buildService(BackEndService::class.java)
                val requestCall = service.fetchEvent(id, code)
                requestCall.enqueue(object : retrofit2.Callback<Event> {
                    override fun onFailure(call: Call<Event>, t: Throwable) {
                        Toast.makeText(this@JoinNewEvent, "Problem occured", Toast.LENGTH_SHORT).show()
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onResponse(call: Call<Event>, response: Response<Event>) {
                        if (response.isSuccessful) {
                            //Toast.makeText(this@JoinEventFragment.context, response.body()?.name,Toast.LENGTH_SHORT).show()
                            var result = response.body()!!
                            var id = result.id
                            var name = result.name
                            var location = result.location
                            var startDate = result.start_date
                            var endDate = result.end_date
                            var description = result.description
                            var lon = result.longitude
                            var lat = result.latitude
                            var ecode = result.code

                            CurrentEvent.setEventDetails(
                                id.toString(),
                                name,
                                location,
                                startDate,
                                endDate,
                                description,
                                lon,
                                lat,
                                ecode,
                                "123"
                            )
                            registerParticipant()
                            var intent = Intent(this@JoinNewEvent, EventMenuParticipant::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this@JoinNewEvent, "Invalid EventID or EventCode", Toast.LENGTH_SHORT).show()
                        }
                    }


                })


            }
        }
    }


    fun registerParticipant(){
        val  s  = ServiceBuilder.buildService((BackEndService::class.java))
        val rCall = s.register(CurrentEvent.id, CurrentUser.getUser())

        rCall.enqueue(object : retrofit2.Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful){
                    var action = response.body()!!
                    Toast.makeText(this@JoinNewEvent,action, Toast.LENGTH_SHORT).show()
                    return
                }

            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                //Toast.makeText(this@JoinNewEvent,"Failed to Insert",Toast.LENGTH_SHORT).show()
            }


        })

    }
}
