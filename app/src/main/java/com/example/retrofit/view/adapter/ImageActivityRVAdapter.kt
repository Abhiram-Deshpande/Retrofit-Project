package com.example.retrofit.view.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit.R
import com.example.retrofit.model.Image
import com.example.retrofit.view.activities.ImagesActivity

class ImageActivityRVAdapter(private val context:Context): RecyclerView.Adapter<ImageActivityRVAdapter.ViewHolder>() {

    private lateinit var imageList:List<Image>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = context.getSystemService(LayoutInflater::class.java).inflate(R.layout.image,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var holderView = holder.view
        var imageUrl = imageList[position].url
        Glide.with(context).load(Uri.parse(imageUrl)).into(holder.imageView)
        holder.title.text = imageList[position].title

    }

    override fun getItemCount(): Int {
            return imageList.size
    }

    class ViewHolder(var itemView:View):RecyclerView.ViewHolder(itemView){
        var view = itemView
        var imageView = itemView.findViewById<ImageView>(R.id.imageView)
        var title = itemView.findViewById<TextView>(R.id.image_description)
    }

    fun setImageList(imageList:List<Image>){
        this.imageList = imageList
    }
}