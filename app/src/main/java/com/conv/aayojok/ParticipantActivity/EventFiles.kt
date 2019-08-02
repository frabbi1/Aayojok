package com.conv.aayojok.ParticipantActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.conv.aayojok.Adapter.FileAdapter
import com.conv.aayojok.Models.CurrentEvent
import com.conv.aayojok.Models.File
import com.conv.aayojok.R
import com.conv.aayojok.services.BackEndService
import com.conv.aayojok.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_event_files.*
import retrofit2.Call
import retrofit2.Response

class EventFiles : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_files)

        var lm = LinearLayoutManager(this)
        event_files_rec_view.layoutManager = lm

        val service = ServiceBuilder.buildService(BackEndService::class.java)
        var requestCall = service.getFile(CurrentEvent.id)

        requestCall.enqueue(object : retrofit2.Callback<List<File>>{
            override fun onResponse(call: Call<List<File>>, response: Response<List<File>>){
                if(response.isSuccessful){
                    var fl = response.body()!!
                    //ParticipantSupplier.setList(p,p.name)
                    //var f = File("file 1","http://ec2-52-29-113-22.eu-central-1.compute.amazonaws.com/ema/uploads/photos/1559018506997download.jpg" )
                    //var fl = listOf<File>(f)
                    //var x = listOf<String>("http://ec2-52-29-113-22.eu-central-1.compute.amazonaws.com/ema/uploads/photos/1559018506997download.jpg","http://ec2-52-29-113-22.eu-central-1.compute.amazonaws.com/ema/uploads/photos/1559018506994bg3.jpg","http://ec2-52-29-113-22.eu-central-1.compute.amazonaws.com/ema/uploads/photos/1558969922329details.png")
                    val adapter =  FileAdapter(this@EventFiles, fl)
                    event_files_rec_view.adapter = adapter
                    //Toast.makeText(this@Files, "hoise", Toast.LENGTH_SHORT)
                    //   .show()



                }else{
                    Toast.makeText(this@EventFiles, "Failed to retrieve details ", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<List<File>>, t: Throwable) {
                //finish()
                Toast.makeText(this@EventFiles, "Failed to retrieve details. Check Internet Connection", Toast.LENGTH_SHORT)
                    .show()

                var i  = Intent(this@EventFiles, ParticipantMainActivity::class.java)
                startActivity(i)

            }
        })
    }

}
