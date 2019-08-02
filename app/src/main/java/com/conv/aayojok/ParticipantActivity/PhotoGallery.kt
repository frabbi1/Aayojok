package com.conv.aayojok.ParticipantActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.conv.aayojok.Adapter.GalleryAdapter
import com.conv.aayojok.Models.CurrentEvent
import com.conv.aayojok.R
import com.conv.aayojok.services.BackEndService
import com.conv.aayojok.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_photo_gallery.*
import retrofit2.Call
import retrofit2.Response

class PhotoGallery : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_gallery)

        var lm = LinearLayoutManager(this)
        gallery.layoutManager = lm


//        var x = listOf<String>("http://ec2-52-29-113-22.eu-central-1.compute.amazonaws.com/ema/uploads/photos/1559018506997download.jpg","http://ec2-52-29-113-22.eu-central-1.compute.amazonaws.com/ema/uploads/photos/1559018506994bg3.jpg","http://ec2-52-29-113-22.eu-central-1.compute.amazonaws.com/ema/uploads/photos/1558969922329details.png")
//        val adapter =  GalleryAdapter(this@Gallery, x)
//        gallery.adapter = adapter
        val service = ServiceBuilder.buildService(BackEndService::class.java)
        var requestCall = service.getPhoto(CurrentEvent.id.toString())

        requestCall.enqueue(object : retrofit2.Callback<List<String>>{
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>){
                if(response.isSuccessful){
                    var p = response.body()!!
                    //ParticipantSupplier.setList(p,p.name)
                    //var x = listOf<String>("http://ec2-52-29-113-22.eu-central-1.compute.amazonaws.com/ema/uploads/photos/1559018506997download.jpg","http://ec2-52-29-113-22.eu-central-1.compute.amazonaws.com/ema/uploads/photos/1559018506994bg3.jpg","http://ec2-52-29-113-22.eu-central-1.compute.amazonaws.com/ema/uploads/photos/1558969922329details.png")
                    val adapter =  GalleryAdapter(this@PhotoGallery, p)
                    gallery.adapter = adapter
                    //Toast.makeText(this@Gallery, "hoise", Toast.LENGTH_SHORT)
                    //  .show()



                }else{
                    Toast.makeText(this@PhotoGallery, "Failed to retrieve details ", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                //finish()
                Toast.makeText(this@PhotoGallery, "Failed to retrieve details :( " + t.toString(), Toast.LENGTH_SHORT)
                    .show()
                var i = Intent(this@PhotoGallery, ParticipantMainActivity::class.java)
                startActivity(i)

            }
        })


    }



}
