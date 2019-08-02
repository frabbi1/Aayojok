package com.conv.aayojok.Adapter

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.conv.aayojok.R
import kotlinx.android.synthetic.main.card_view_photo.view.*


class GalleryAdapter(val context: Context, val photos:List<String>) : RecyclerView.Adapter<GalleryAdapter.myViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_view_photo,p0,false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: myViewHolder, pos: Int) {
        val photo = photos[pos]
        holder.setData(photo,pos)
    }

    inner class myViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var current:String? = null
        var curpos = 0
        init {
            itemView.setOnClickListener {

            }
            itemView.btn_download_pic.setOnClickListener {
                var dm = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                var uri = Uri.parse(current)
                var req = DownloadManager.Request(uri)
                req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                var ref = dm.enqueue(req)
            }
        }
        fun setData(p:String, pos: Int){
            Glide.with(context).load(p).error(Glide.with(context).load(R.drawable.blank_pro_pic)).into(itemView.img)
            current = p

        }
    }
}


