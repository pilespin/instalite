package com.example.instalite.photo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.instalite.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.photo_row.view.*
import kotlinx.android.synthetic.main.user_row.view.name


class PhotoAdapter(val photos: MutableList<Photo>): RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.photo_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = photos[position].title
        Picasso.get().load(photos[position].url).into(holder.image)
//        Picasso.get().load(photos[position].thumbnailUrl).into(holder.image)
    }

    override fun getItemCount() = photos.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.name
        val image: ImageView = itemView.image
    }

}