package com.example.rickandmortyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        fetchCharacters()
    }

    private fun fetchCharacters() {
        val api = NetworkUtils
            .getRetrofitInstance("https://rickandmortyapi.com/api/")

        val endpoint = api.create(RickAndMortyApi::class.java)
        val callback = endpoint.getCharacters(1)

        callback.enqueue(object : Callback<CharacterResponse>{
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                if (response.isSuccessful){
                    val characters = response.body()?.results
                    Log.d("MainActivity", "Characters: $characters")
                } else {
                    Log.e("MainActivity", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.e("MainActivity", "Failure: ${t.message}")
            }
        })
    }

}
