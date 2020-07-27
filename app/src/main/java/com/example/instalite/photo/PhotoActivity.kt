package com.example.instalite.photo

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
import com.example.instalite.album.Album
import kotlinx.android.synthetic.main.activity_main.loading
import kotlinx.android.synthetic.main.activity_photo.*
import org.json.JSONObject
import java.lang.Exception

class PhotoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        try {
            val album : Album = intent.extras?.getParcelable("album")!!
            title = album.title
//            Log.i("---------LOG---------", album.toString())

            // Instantiate the RequestQueue.
            val queue = Volley.newRequestQueue(this)
            val url = "https://jsonplaceholder.typicode.com/users/${album.userId}/photos?albumId=${album.id}"
            val jsonObjectRequest = JsonArrayRequest(
                Request.Method.GET, url, null,
                Response.Listener { response ->
                    Log.i("---------LOG---------", "GET: userId: ${album.userId}, ${response.length()} Photos")
                    val allPhotos = mutableListOf<Photo>()
                    for (i in 0 until response.length()){
                        val json: JSONObject = response.getJSONObject(i)
                        val photo = Photo(
                            json.getInt("id"),
                            json.getInt("albumId"),
                            json.get("title").toString(),
                            json.get("url").toString(),
                            json.get("thumbnailUrl").toString()
                        )
                        allPhotos.add(photo)
//                    Log.i("---------LOG---------", album.toString())
                    }
                    loading.visibility = View.GONE
                    listPhotos.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                    listPhotos.adapter =
                        PhotoAdapter(allPhotos)
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