package com.example.retrofit.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.R
import com.example.retrofit.model.Contributor


class MainActivityRVAdapter( private val context: Context): RecyclerView.Adapter<MainActivityRVAdapter.ViewHolder>() {
    private lateinit var contributors:List<Contributor>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view= context.getSystemService(LayoutInflater::class.java).inflate(R.layout.contributor,parent,false)
        return ViewHolder(view)
    }

    private var onClickListener:OnClickListener? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.body.text = contributors.get(position).body
        holder.id.text = contributors[position].id.toString()
        holder.title.text = contributors[position].title
        holder.view.setOnClickListener{
            if(this.onClickListener!=null){
                onClickListener?.onClick(position,contributors[position],holder.view)
            }
        }
    }

    override fun getItemCount(): Int {
        return contributors.size
    }

     class ViewHolder(var itemView:View):RecyclerView.ViewHolder(itemView){
         var view = this.itemView
        val id:TextView = itemView.findViewById(R.id.id)
        val title:TextView = itemView.findViewById(R.id.title)
        val body:TextView = itemView.findViewById(R.id.body)
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, model: Contributor,sharedElement:View)
    }
    fun setContributors(contributors:List<Contributor>){
        this.contributors = contributors
    }
}