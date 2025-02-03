package com.example.rickandmortyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val characterAdapter: CharacterAdapter by lazy {
        CharacterAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchCharacters{ characterAdapter.submitList(it) }
        binding.rvRecycler
            .apply{
                layoutManager= LinearLayoutManager(this@MainActivity)
                adapter = characterAdapter}

    }

    private fun fetchCharacters(onResult:(List<Character>)->Unit){
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
                    val listCharacter = response.body()?.results ?: emptyList()
                    onResult(listCharacter)
                } else {
                    Log.e("MainActivity", "Error: ${response.code()}")
                    onResult(emptyList())
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.e("MainActivity", "Failure: ${t.message}")
                onResult(emptyList())
            }
        })
    }

}
