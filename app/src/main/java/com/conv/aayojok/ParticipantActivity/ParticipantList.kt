package com.conv.aayojok.ParticipantActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.conv.aayojok.Adapter.ParticipantAdapter
import com.conv.aayojok.Models.CurrentEvent
import com.conv.aayojok.Models.User
import com.conv.aayojok.R
import com.conv.aayojok.services.BackEndService
import com.conv.aayojok.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_participant_list.*
import retrofit2.Call
import retrofit2.Response

class ParticipantList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_participant_list)

        var lm = LinearLayoutManager(this)
        participant.layoutManager = lm

        val service = ServiceBuilder.buildService(BackEndService::class.java)
        var requestCall = service.getAllParticipants(CurrentEvent.id.toString())

        requestCall.enqueue(object : retrofit2.Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>){
                if(response.isSuccessful){
                    var p = response.body()!!
                    Toast.makeText(this@ParticipantList, "hoise"+p.size, Toast.LENGTH_SHORT)
                    //ParticipantSupplier.setList(p,p.name)
                    val adapter =  ParticipantAdapter(this@ParticipantList, p.reversed())
                    participant.adapter = adapter
                    //Toast.makeText(this@ParticipantListActivity, "hoise", Toast.LENGTH_SHORT)
                    //   .show()



                }else{
                    Toast.makeText(this@ParticipantList, "Failed to retrieve details ", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                //finish()
                Toast.makeText(this@ParticipantList, "Failed to retrieve details :( $t", Toast.LENGTH_SHORT)
                    .show()

                var i = Intent(this@ParticipantList, ParticipantMainActivity::class.java)
                startActivity(i)


            }
        })




    }
}
