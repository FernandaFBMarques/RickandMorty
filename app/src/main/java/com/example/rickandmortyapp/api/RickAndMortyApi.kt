package com.example.rickandmortyapp.api

import com.example.rickandmortyapp.ui.character.dto.CharacterResponse
import retrofit2.Response
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

// Essa interface sera utilizada pelo retrofit para consumir as infos

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): Response<CharacterResponse>

}