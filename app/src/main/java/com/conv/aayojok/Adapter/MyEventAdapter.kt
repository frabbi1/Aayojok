package com.conv.aayojok.Adapter

import android.content.Context
import android.content.Intent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.conv.aayojok.ParticipantActivity.EventMenuParticipant
import com.conv.aayojok.Models.CurrentEvent
import com.conv.aayojok.Models.Event
import com.conv.aayojok.R
import kotlinx.android.synthetic.main.card_view_my_event.view.*
import java.text.SimpleDateFormat
import java.util.*

class MyEventAdapter(val context: Context, val events:List<Event>) : RecyclerView.Adapter<MyEventAdapter.myViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_view_my_event, p0, false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(holder: myViewHolder, pos: Int) {
        val event = events[pos]
        holder.setData(event, pos)
    }
    inner class myViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var current:Event? = null
        var curpos = 0
        init {
            itemView.setOnClickListener {

                //CurrentEvent.setEventDetails(current!!.id,current!!.name,current!!.location,current!!.start_date,current!!.end_date,current!!.description,current!!.longitude,current!!.latitude)
                /*var intent = Intent(this@MyEventAdapter.context, EventMenuAdmin::class.java)
                context.startActivity(intent)
                Toast.makeText(this@MyEventAdapter.context,"Clicked", Toast.LENGTH_SHORT).show()*/

                CurrentEvent.setEventDetails(
                    current!!.id.toString(),
                    current!!.name,
                    current!!.location,
                    current!!.start_date,
                    current!!.end_date,
                    current!!.description,
                    current!!.longitude,
                    current!!.latitude,
                    current!!.code,
                    "123"
                )
                var intent = Intent(this@MyEventAdapter.context, EventMenuParticipant::class.java)
                context.startActivity(intent)
                //Toast.makeText(this@MyEventAdapter.context,"Clicked", Toast.LENGTH_SHORT).show()
            }
        }
        fun setData(e:Event, pos: Int){

            this.curpos = pos
            this.current = e

            itemView.event_name.text = e.name






            var sdf = SimpleDateFormat("yyyy-MM-dd")
            var d1 = sdf.parse(e.start_date)
            var d2 = sdf.parse(e.end_date)
            var currentDate = sdf.format(Date())
            var cdate = sdf.parse(currentDate)

            //Toast.makeText(this@MyEventAdapter.context,cdate.toString(), Toast.LENGTH_SHORT).show()

            var status: String

            if(d1.after(cdate)){
                status = "Not Started"
            }
            else if(d2.before(cdate)){
                status = "Ended"
            }
            else{
                status = "On Going"
            }

            itemView.status.text = status






        }
    }
}


