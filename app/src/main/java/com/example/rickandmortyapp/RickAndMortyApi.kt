package com.example.rickandmortyapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// Essa interface sera utilizada pelo retrofit para consumir as infos

interface RickAndMortyApi {

    @GET("character")
    fun getCharacters(@Query("page")page:Int): Call<CharacterResponse>

}