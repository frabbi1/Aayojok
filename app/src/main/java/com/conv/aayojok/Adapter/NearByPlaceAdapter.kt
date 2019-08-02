package com.conv.aayojok.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.conv.aayojok.Models.Place
import com.conv.aayojok.ParticipantActivity.NearByPlace
import com.conv.aayojok.R
import kotlinx.android.synthetic.main.card_view_nearby_places.view.*

class NearByPlaceAdapter(val context: Context, val places:List<Place>) : RecyclerView.Adapter<NearByPlaceAdapter.myViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_view_nearby_places, p0, false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return places.size
    }

    override fun onBindViewHolder(holder: myViewHolder, pos: Int) {
        val place = places[pos]
        holder.setData(place, pos)
    }

    inner class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var current: Place? = null
        var curpos = 0

        init {
            itemView.btn_show.setOnClickListener {


                var intent = Intent(this@NearByPlaceAdapter.context, NearByPlace::class.java)
                intent.putExtra("lon", current!!.lon)
                intent.putExtra("lat", current!!.lat)
                intent.putExtra("loc", current!!.loc)
                context.startActivity(intent)
            }
        }

        fun setData(p: Place, pos: Int) {
            itemView.place_name.text = p.loc
            //Toast.makeText(context, p.photo,Toast.LENGTH_LONG).show()
            //Glide.with(context).load(p.photo).error(Glide.with(context).load(R.drawable.blank_pro_pic)).into(itemView.proPicCard)
            this.current = p
            this.curpos = pos
        }
    }
}