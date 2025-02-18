package com.example.rickandmortyapp.viewmodel

import android.util.Log
import com.example.rickandmortyapp.model.Character
import com.example.rickandmortyapp.model.CharacterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FetchCharacters {
    companion object{
        fun fetchCharacters(page: Int, onResult:(List<Character>)->Unit){
            val api = NetworkUtils.getRetrofitInstance("https://rickandmortyapi.com/api/")
            val endpoint = api.create(RickAndMortyApi::class.java)
            val callback = endpoint.getCharacters(page)

            callback.enqueue(object : Callback<CharacterResponse> {
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
}