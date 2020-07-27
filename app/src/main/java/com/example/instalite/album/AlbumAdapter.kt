package com.example.instalite.album

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.instalite.photo.PhotoActivity
import com.example.instalite.R
import kotlinx.android.synthetic.main.user_row.view.*


class AlbumAdapter(val context : Activity, val albums: MutableList<Album>): RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.album_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = albums[position].title
        holder.card.setOnClickListener {
            val intent = Intent(context, PhotoActivity::class.java)
            intent.putExtra("album", albums[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = albums.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.name
        val card: CardView = itemView.card
    }

}