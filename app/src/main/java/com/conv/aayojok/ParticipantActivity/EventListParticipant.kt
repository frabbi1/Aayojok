package com.conv.aayojok.ParticipantActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.conv.aayojok.Adapter.MyEventAdapter
import com.conv.aayojok.Models.CurrentUser
import com.conv.aayojok.Models.Event
import com.conv.aayojok.Models.EventSupplier
import com.conv.aayojok.R
import com.conv.aayojok.services.BackEndService
import com.conv.aayojok.services.ServiceBuilder
import kotlinx.android.synthetic.main.fragment_my_event.*
import retrofit2.Call
import retrofit2.Response

class EventListParticipant : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_my_event)

        var lm = LinearLayoutManager(this)

        event_rec_view.layoutManager = lm

        val service = ServiceBuilder.buildService(BackEndService::class.java)
        var rcall = service.getJoinedEvents(CurrentUser.getUser())
        rcall.enqueue(object : retrofit2.Callback<List<Event>>{
            override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>){
                if(response.isSuccessful){
                    var e = response.body()!!.toMutableList()
                    EventSupplier.eventList = e
                    val adapter =  MyEventAdapter(this@EventListParticipant, EventSupplier.getList())
                    event_rec_view.adapter = adapter
                    //ParticipantSupplier.setList(response.body()!!)

                    //Toast.makeText(this@EventsFragmnet.context!!, "hoise", Toast.LENGTH_SHORT)
                    //  .show()



                }else{
                    Toast.makeText(this@EventListParticipant, "Failed to retrieve details ", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<List<Event>>, t: Throwable) {

                Toast.makeText(this@EventListParticipant, "Failed to retrieve details :( " + t.toString(), Toast.LENGTH_SHORT)
                    .show()

            }
        })




        /*var event1 = Event(
            1,"MyEvent 1", "IIT",
            "1/1/2018", "2/2/1018",
            "lalalalala", "12.13", "12.13", "2","1")
        var event2 = Event(
            1,"Devops Workshop IIT Organized", "IIT",
            "1/1/2018", "2/2/1018",
            "lalalalala", "12.13", "12.13", "2","1")
        EventSupplier.addEvent(event1)
        EventSupplier.addEvent(event2)*/


       /* if(EventSupplier.eventList.size ==0 )tv_text.text = "No Event Yet"
        else tv_text.text = "All Event(s)"

        val adapter = MyEventAdapter(this, EventSupplier.getList())
        event_rec_view.adapter = adapter*/
    }

    override fun onBackPressed() {
        var i = Intent(this, ParticipantMainActivity::class.java)
        startActivity(i)
    }


}
