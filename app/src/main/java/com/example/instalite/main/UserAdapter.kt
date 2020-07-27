package com.example.instalite.main

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.instalite.album.AlbumActivity
import com.example.instalite.R
import kotlinx.android.synthetic.main.user_row.view.*


class UserAdapter(val context : Activity, val users: MutableList<User>): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.user_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = users[position].name
        holder.username.text = users[position].username
        holder.email.text = users[position].email
        holder.phone.text = users[position].phone
        holder.website.text = users[position].website
        holder.card.setOnClickListener {
            val intent = Intent(context, AlbumActivity::class.java)
            intent.putExtra("user", users[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = users.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.name
        val username: TextView = itemView.username
        val email: TextView = itemView.email
        val phone: TextView = itemView.phone
        val website: TextView = itemView.website
        val card: CardView = itemView.card
    }

}