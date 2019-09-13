package com.conv.aayojok.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.conv.aayojok.Models.RP
import com.conv.aayojok.R
import kotlinx.android.synthetic.main.card_view_rp.view.*


class RPAdapter(val context: Context, val rps:List<RP>) : RecyclerView.Adapter<RPAdapter.myViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_view_rp,p0,false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return rps.size
    }

    override fun onBindViewHolder(holder: myViewHolder, pos: Int) {
        val n = rps[pos]
        holder.setData(n,pos)
    }

    inner class myViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var current: RP? = null
        var curpos = 0

        fun setData(p:RP, pos: Int){
            itemView.txtName.text = p.name
            itemView.txtEmail.text = p.email
            itemView.txtAbout.text = p.details
            //Toast.makeText(context, p.photo,Toast.LENGTH_LONG).show()
            //Glide.with(context).load(p.photo).error(Glide.with(context).load(R.drawable.blank_pro_pic)).into(itemView.proPicCard)
            this.current = p
            this.curpos = pos
        }
    }
}