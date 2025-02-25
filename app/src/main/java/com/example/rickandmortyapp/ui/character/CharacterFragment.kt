package com.example.rickandmortyapp.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.databinding.CharacterFragmentBinding
import com.example.rickandmortyapp.ui.character.list.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CharacterFragment : Fragment() {
    private val viewModel: CharacterViewModel by viewModels()

    private val RecyclerView.characterAdapter: CharacterAdapter?
        get() = adapter as? CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.character_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(CharacterFragmentBinding.bind(view)) {
            setupBindings(this)
            registerViewStateListener(this)
        }
    }

    private fun setupBindings(binding: CharacterFragmentBinding) {
        binding.characterRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = CharacterAdapter()
        }
    }

    private fun registerViewStateListener(binding: CharacterFragmentBinding) {
        viewModel.viewState
            .onEach {
                binding.characterRecyclerView
                    .characterAdapter
                    ?.submitList(it.characters)

                //TODO: Handle loading and errors
            }.launchIn(lifecycleScope)
    }
}