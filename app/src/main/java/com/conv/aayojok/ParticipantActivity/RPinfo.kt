package com.conv.aayojok.ParticipantActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.conv.aayojok.Adapter.NoticeAdapter
import com.conv.aayojok.Adapter.RPAdapter
import com.conv.aayojok.Adapter.RPFalseAdapter
import com.conv.aayojok.Models.CurrentEvent
import com.conv.aayojok.Models.RP
import com.conv.aayojok.R
import com.conv.aayojok.services.BackEndService
import com.conv.aayojok.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_rpinfo.*
import retrofit2.Call
import retrofit2.Response

class RPinfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rpinfo)

        var lm = LinearLayoutManager(this)
        event_rp.layoutManager = lm

        val service = ServiceBuilder.buildService(BackEndService::class.java)
        var requestCall = service.getRPinfo(CurrentEvent.id.toString())

        requestCall.enqueue(object : retrofit2.Callback<List<RP>>{
            override fun onResponse(call: Call<List<RP>>, response: Response<List<RP>>){
                if(response.isSuccessful){
                    var p = response.body()!!

                    if(p.size==0){
                        val adapter =  RPFalseAdapter(this@RPinfo, "")
                        event_rp.adapter = adapter
                    }else{
                        val adapter =  RPAdapter(this@RPinfo, p)
                        event_rp.adapter = adapter
                    }
                    //ParticipantSupplier.setList(p,p.name)

                    //Toast.makeText(this@Notification, "hoise", Toast.LENGTH_SHORT)
                    //  .show()



                }else{
                    Toast.makeText(this@RPinfo, "Failed to retrieve details.", Toast.LENGTH_SHORT)
                        .show()


                }
            }

            override fun onFailure(call: Call<List<RP>>, t: Throwable) {
                finish()
                Toast.makeText(this@RPinfo, "No Details, SORRY", Toast.LENGTH_SHORT)
                    .show()

                var i = Intent(this@RPinfo, EventMenuParticipant::class.java)
                startActivity(i)

            }
        })




    }
}
