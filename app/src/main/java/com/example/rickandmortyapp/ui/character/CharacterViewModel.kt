package com.example.rickandmortyapp.ui.character

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.helper.getStateFlow
import com.example.rickandmortyapp.ui.character.dto.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize

@HiltViewModel
class CharacterViewModel @Inject constructor(
    savedState: SavedStateHandle,
    private val repository: CharacterRepository
) : ViewModel() {
    private val _viewState = savedState.getStateFlow(
        scope = viewModelScope,
        initialValue = ViewState()
    )
    val viewState: StateFlow<ViewState> = _viewState
    
    init {
        requestDataFor(page = FIRST_PAGE)
    }

    private fun requestDataFor(page: Int) {
        _viewState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            repository.fetchCharacters(page)
                .fold(
                    onSuccess = ::onCharactersReceived,
                    onFailure = { onCharactersRequestFailed() }
                )
        }
    }

    private fun onCharactersReceived(characters: List<Character>) {
        _viewState.update {
            it.copy(
                isLoading = false,
                characters = characters
            )
        }
    }

    private fun onCharactersRequestFailed() {
        _viewState.update { it.copy(isLoading = false) }
        //TODO: Display an error message to the user,
        // remember to do this in a way that uses the ViewState
    }

    @Parcelize
    data class ViewState(
        val characters: List<Character> = emptyList(),
        val isLoading: Boolean = false
    ) : Parcelable

    companion object {
        private const val FIRST_PAGE = 1
    }
}