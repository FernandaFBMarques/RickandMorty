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
    private var character: List<Character> = emptyList()
    private val characterAdapter: CharacterAdapter by lazy {
        CharacterAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        character = fetchCharacters()
        binding.rvRecycler
            .apply{
                layoutManager= LinearLayoutManager(this@MainActivity)
                adapter = characterAdapter}

        binding.apply{
            characterAdapter.submitList(character)
        }
    }

    private fun fetchCharacters() : List<Character>{
        val api = NetworkUtils
            .getRetrofitInstance("https://rickandmortyapi.com/api/")

        val endpoint = api.create(RickAndMortyApi::class.java)
        val callback = endpoint.getCharacters(1)
        var listCharacter = emptyList<Character>()

        callback.enqueue(object : Callback<CharacterResponse>{
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                if (response.isSuccessful){
                    listCharacter = response.body()?.results ?: emptyList()
                    listCharacter?.forEach { character-> Log.d("MainActivity", "Characters: ${character.name}")}

                } else {
                    Log.e("MainActivity", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.e("MainActivity", "Failure: ${t.message}")
            }
        })
        return listCharacter
    }

}
