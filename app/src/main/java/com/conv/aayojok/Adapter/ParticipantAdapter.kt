package com.conv.aayojok.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.conv.aayojok.Models.ParticipantSupplier
import com.conv.aayojok.Models.User
import com.conv.aayojok.ParticipantActivity.ParticipantDetails
import com.conv.aayojok.R
import kotlinx.android.synthetic.main.participant_list.view.*

class ParticipantAdapter(val context: Context, val participants:List<User>) : RecyclerView.Adapter<ParticipantAdapter.myViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.participant_list,p0,false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return participants.size
    }

    override fun onBindViewHolder(holder: myViewHolder, pos: Int) {
        val participant = participants[pos]
        holder.setData(participant,pos)
    }

    inner class myViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var current: User? = null
        var curpos = 0
        init {
            itemView.setOnClickListener {


                //CurrentEvent.setEventDetails(current!!.id,current!!.name,current!!.location,current!!.start_date,current!!.end_date,current!!.description,current!!.longitude,current!!.latitude)
                ParticipantSupplier.setList(current!!.id,current!!.name,current!!.email,current!!.age,current!!.gender,
                    current!!.occupation.toString(),current!!.institution.toString(),current!!.phone,current!!.nationality.toString(),current!!.photo.toString() )
                var intent = Intent(this@ParticipantAdapter.context, ParticipantDetails::class.java)
                context.startActivity(intent)
            }
        }
        fun setData(p:User, pos: Int){
            itemView.name_field.text = p.name
            //Toast.makeText(context, p.photo,Toast.LENGTH_LONG).show()
            Glide.with(context).load(p.photo).error(Glide.with(context).load(R.drawable.blank_pro_pic)).into(itemView.image)
            this.current = p
            this.curpos = pos
        }
    }
}