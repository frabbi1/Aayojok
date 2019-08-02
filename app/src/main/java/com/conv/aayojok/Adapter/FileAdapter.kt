package com.conv.aayojok.Adapter

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.conv.aayojok.Models.File
import com.conv.aayojok.R
import kotlinx.android.synthetic.main.card_view_files.view.*

class FileAdapter(val context: Context, val files:List<File>) : RecyclerView.Adapter<FileAdapter.myViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_view_files,p0,false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return files.size
    }

    override fun onBindViewHolder(holder: myViewHolder, pos: Int) {
        val file = files[pos]
        holder.setData(file,pos)
    }

    inner class myViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var current:String? = null
        var curpos = 0
        init {
            itemView.btn_download.setOnClickListener {
                var dm = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                var uri = Uri.parse(current)
                var req = DownloadManager.Request(uri)
                req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                var ref = dm.enqueue(req)
                /*var i = Intent(Intent.ACTION_VIEW, Uri.parse(current))
                context.startActivity(i)*/

            }
        }
        fun setData(f: File, pos: Int){
            itemView.filename.text = f.name
            current = f.url

        }
    }
}