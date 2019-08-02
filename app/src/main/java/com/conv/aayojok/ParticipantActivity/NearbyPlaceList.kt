package com.conv.aayojok.ParticipantActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.conv.aayojok.Adapter.NearByPlaceAdapter
import com.conv.aayojok.Models.CurrentEvent
import com.conv.aayojok.Models.Place
import com.conv.aayojok.R
import com.conv.aayojok.services.BackEndService
import com.conv.aayojok.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_nearby_place_list.*
import retrofit2.Call
import retrofit2.Response

class NearbyPlaceList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nearby_place_list)

        var lm = LinearLayoutManager(this)
        event_nearby_rec_view.layoutManager = lm

        val service = ServiceBuilder.buildService(BackEndService::class.java)
        var requestCall = service.getPlace(CurrentEvent.id.toString())

        requestCall.enqueue(object : retrofit2.Callback<List<Place>>{
            override fun onResponse(call: Call<List<Place>>, response: Response<List<Place>>){
                if(response.isSuccessful){
                    var p = response.body()!!
                    //ParticipantSupplier.setList(p,p.name)
                    val adapter =  NearByPlaceAdapter(this@NearbyPlaceList, p)
                    event_nearby_rec_view.adapter = adapter
                    //Toast.makeText(this@NearMe, "hoise", Toast.LENGTH_SHORT)
                    // .show()



                }else{
                    Toast.makeText(this@NearbyPlaceList, "Failed to retrieve details ", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<List<Place>>, t: Throwable) {
                //finish()
                Toast.makeText(this@NearbyPlaceList, "Failed to retrieve details :( " + t.toString(), Toast.LENGTH_SHORT)
                    .show()
                var i = Intent(this@NearbyPlaceList, ParticipantMainActivity::class.java)
                startActivity(i)

            }
        })



    }
}
