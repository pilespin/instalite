package com.example.instalite.album

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.instalite.R
import com.example.instalite.main.User
import kotlinx.android.synthetic.main.activity_album.*
import kotlinx.android.synthetic.main.activity_main.loading
import org.json.JSONObject
import java.lang.Exception

class AlbumActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        try {
            val user : User = intent.extras?.getParcelable("user")!!
            title = user.name
//            Log.i("---------LOG---------", user.toString())

            // Instantiate the RequestQueue.
            val queue = Volley.newRequestQueue(this)
            val url = "https://jsonplaceholder.typicode.com/users/${user.id}/albums"
            val jsonObjectRequest = JsonArrayRequest(
                Request.Method.GET, url, null,
                Response.Listener { response ->
                    Log.i("---------LOG---------", "GET: userId: ${user.id}, ${response.length()} Album")
                    val allAlbums = mutableListOf<Album>()
                    for (i in 0 until response.length()){
                        val json: JSONObject = response.getJSONObject(i)
                        val album = Album(
                            json.getInt("id"),
                            json.getInt("userId"),
                            json.get("title").toString()
                        )
                        allAlbums.add(album)
//                    Log.i("---------LOG---------", album.toString())
                    }
                    loading.visibility = View.GONE
                    listAlbums.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                    listAlbums.adapter =
                        AlbumAdapter(this, allAlbums)
                },
                Response.ErrorListener { error ->
                    loading.text = getString(R.string.errorLoading)
                }
            )
            // Add the request to the RequestQueue.
            jsonObjectRequest.setShouldCache(true)
            queue.add(jsonObjectRequest)

        } catch (e: Exception) {
            loading.text = getString(R.string.errorLoading)
            return
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}