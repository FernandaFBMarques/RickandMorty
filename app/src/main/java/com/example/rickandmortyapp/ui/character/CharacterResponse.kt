package com.example.rickandmortyapp.ui.character

import com.example.rickandmortyapp.Info
import com.google.gson.annotations.SerializedName

data class CharacterResponse (
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Character>
)