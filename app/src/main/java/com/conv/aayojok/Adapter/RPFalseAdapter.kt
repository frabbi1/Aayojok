package com.conv.aayojok.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.conv.aayojok.Models.RP
import com.conv.aayojok.R
import kotlinx.android.synthetic.main.card_view_rp.view.*


class RPFalseAdapter(val context: Context, val rps:String) : RecyclerView.Adapter<RPFalseAdapter.myViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_view_rp,p0,false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: myViewHolder, pos: Int) {

    }

    inner class myViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var current: RP? = null
        var curpos = 0


    }
}