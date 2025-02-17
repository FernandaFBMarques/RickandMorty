package com.example.rickandmortyapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapp.viewmodel.CharacterAdapter
import com.example.rickandmortyapp.databinding.ActivityMainBinding
import com.example.rickandmortyapp.viewmodel.FetchCharacters
import androidx.core.widget.NestedScrollView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentPage = 1
    private var totalCharacters = 20
    private val characterAdapter: CharacterAdapter by lazy {
        CharacterAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //FetchCharacters.fetchCharacters(currentPage){ characterAdapter.submitList(it) }

        binding.apply {
            charactersView.setOnScrollChangeListener (NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, oldScrollY ->
                if(scrollY > oldScrollY){
                    if(!v.canScrollVertically(1)){
                        FetchCharacters.fetchCharacters(currentPage){ characterAdapter.submitList(it) }
                        currentPage++

                    }
                }
            })
        }

        binding.rvRecycler
            .apply{
                layoutManager= LinearLayoutManager(this@MainActivity)
                adapter = characterAdapter}

    }
}
