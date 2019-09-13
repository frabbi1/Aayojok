package com.conv.aayojok.ParticipantActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.conv.aayojok.Adapter.NoticeAdapter
import com.conv.aayojok.Models.CurrentEvent
import com.conv.aayojok.R
import com.conv.aayojok.services.BackEndService
import com.conv.aayojok.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_notices.*
import retrofit2.Call
import retrofit2.Response

class Notices : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notices)

        var lm = LinearLayoutManager(this)
        event_notices.layoutManager = lm

        val service = ServiceBuilder.buildService(BackEndService::class.java)
        var requestCall = service.getNotification(CurrentEvent.id.toString())

        requestCall.enqueue(object : retrofit2.Callback<List<String>>{
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>){
                if(response.isSuccessful){
                    var p = response.body()!!.reversed()
                    //ParticipantSupplier.setList(p,p.name)
                    val adapter =  NoticeAdapter(this@Notices, p)
                    event_notices.adapter = adapter
                    //Toast.makeText(this@Notification, "hoise", Toast.LENGTH_SHORT)
                    //  .show()



                }else{
                    Toast.makeText(this@Notices, "Failed to retrieve details.", Toast.LENGTH_SHORT)
                        .show()


                }
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                finish()
                Toast.makeText(this@Notices, "Failed to retrieve details :( $t", Toast.LENGTH_SHORT)
                    .show()

                var i = Intent(this@Notices, EventMenuParticipant::class.java)
                startActivity(i)

            }
        })


    }


}
