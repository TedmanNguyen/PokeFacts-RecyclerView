package com.codepath.pokemonview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DividerItemDecoration
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var pokeList: MutableList<Triple<String, String, String>>
    private lateinit var rvPoke: RecyclerView
    var pokeImageURL = ""
    var pokeNameURL = ""
    var pokeWeightURL = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPoke = findViewById(R.id.poke_list)
        pokeList = mutableListOf()
        for (i in 0 until 20) {
            getPokeData()
        }

        Log.d("Pokemon List", pokeList.toString())
    }

    private fun getPokeData() {
        val client = AsyncHttpClient()
        val poke = Random.nextInt(600)
        client["https://pokeapi.co/api/v2/pokemon/$poke", object : JsonHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                json: JsonHttpResponseHandler.JSON
            ) {
                pokeImageURL = json.jsonObject.getJSONObject("sprites").getString("front_default")
                Log.d("PokeImage", "response successful")
                Log.d("pokeImageURL", pokeImageURL)
                pokeNameURL = json.jsonObject.getString("name")
                Log.d("PokeName", "response successful")
                Log.d("pokeNameURL", pokeNameURL)
                pokeWeightURL = json.jsonObject.getString("weight")
                val weight = "Weight: " + pokeWeightURL
                Log.d("PokeWeight", "response successful")
                Log.d("pokeWeightURL", pokeWeightURL)

                pokeList.add(Triple(pokeImageURL, pokeNameURL, weight))
                Log.d("Poke list", pokeList.toString())

                val adapter = PokeAdapter(pokeList)
                rvPoke.layoutManager = LinearLayoutManager(this@MainActivity)
                rvPoke.adapter = PokeAdapter(pokeList)
                rvPoke.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))

            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Poke data error", errorResponse)
            }

        }]
    }
}