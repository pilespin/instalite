package com.example.instalite.main

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.instalite.R
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "InstaLite"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://jsonplaceholder.typicode.com/users"
        val jsonObjectRequest = JsonArrayRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                Log.i("---------LOG---------", "GET: ${response.length()} users")
                val allUsers = mutableListOf<User>()
                for (i in 0 until response.length()){
                    val json: JSONObject = response.getJSONObject(i)
                    val user = User(
                        json.getInt("id"),
                        json.get("name").toString(),
                        json.get("username").toString(),
                        json.get("email").toString(),
                        json.get("phone").toString(),
                        json.get("website").toString()
                    )
                    allUsers.add(user)
//                    Log.w("---------LOG---------", user.toString())

                }
                loading.visibility = View.GONE
                listUsers.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                listUsers.adapter =
                    UserAdapter(this, allUsers)
            },
            Response.ErrorListener { error ->
                loading.text = getString(R.string.errorLoading)
            }
        )
        // Add the request to the RequestQueue.
        jsonObjectRequest.setShouldCache(true)
        queue.add(jsonObjectRequest)

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