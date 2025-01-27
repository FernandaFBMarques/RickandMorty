package com.example.rickandmortyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.rickandmortyapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply{
            //binding.welcome.text = "Sample viewBinding"
            binding.btnSample.setOnClickListener{
                fetchCharacters()
            }
        }
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
                    characters?.forEach { character-> Log.d("MainActivity", "Characters: ${character.name}")}
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
