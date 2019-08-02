package com.conv.aayojok.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager


import com.conv.aayojok.Adapter.MyEventAdapter
import com.conv.aayojok.Models.Event
import com.conv.aayojok.Models.EventSupplier
import com.conv.aayojok.R
import kotlinx.android.synthetic.main.fragment_my_event.*

class MyEventFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my_event,null)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var lm = LinearLayoutManager(this@MyEventFragment.context)

        event_rec_view.layoutManager = lm


        var event1 = Event(
            "1","MyEvent 1", "IIT",
            "1/1/2018", "2/2/1018",
            "lalalalala", "12.13", "12.13", "2","1")
        var event2 = Event(
            "1","Devops Workshop IIT Organized", "IIT",
            "1/1/2018", "2/2/1018",
            "lalalalala", "12.13", "12.13", "2","1")
        EventSupplier.addEvent(event1)
        EventSupplier.addEvent(event2)

        if(EventSupplier.eventList.size ==0 )tv_text.text = "No Event Yet"
        else tv_text.text = "All Event(s)"

        val adapter = MyEventAdapter(this@MyEventFragment.context!!, EventSupplier.getList())
        event_rec_view.adapter = adapter



    }

}
