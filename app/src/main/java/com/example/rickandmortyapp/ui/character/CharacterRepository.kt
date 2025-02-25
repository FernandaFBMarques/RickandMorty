package com.example.rickandmortyapp.ui.character

import com.example.rickandmortyapp.api.RickAndMortyApi
import com.example.rickandmortyapp.ui.character.dto.Character
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val api: RickAndMortyApi
) {
    suspend fun fetchCharacters(page: Int): Result<List<Character>> =
        api.getCharacters(page)
            .takeIf { it.isSuccessful }
            ?.let { Result.success(it.body()?.results ?: emptyList()) }
            ?: Result.failure(CharacterRequestFailedException(page))

    class CharacterRequestFailedException(page: Int) : Exception()
}