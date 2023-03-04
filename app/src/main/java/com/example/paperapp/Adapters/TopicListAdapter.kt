package com.example.paperapp.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paperapp.Activities.ContentActivity
import com.example.paperapp.Model.BookModel
import com.example.paperapp.R


class TopicListAdapter(private val list: ArrayList<BookModel>, private val context: Context):RecyclerView.Adapter<TopicListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        val listImage:ImageView= itemView.findViewById(R.id.list_image)

        val listText:TextView= itemView.findViewById(R.id.list_text)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_layout,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = list[position]

        Glide.with(context.applicationContext)
            .load(currentItem.topicLogo)
            .into(holder.listImage)

        holder.listText.text=currentItem.topicName

        holder.itemView.setOnClickListener {

            val intent= Intent(context, ContentActivity::class.java)

            intent.putExtra("topicPdf",currentItem.topicPdf)

            intent.putExtra("topicName",currentItem.topicName)

            context.startActivity(intent)


        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}