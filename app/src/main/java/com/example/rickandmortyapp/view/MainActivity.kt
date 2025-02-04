package com.example.rickandmortyapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapp.viewmodel.CharacterAdapter
import com.example.rickandmortyapp.databinding.ActivityMainBinding
import com.example.rickandmortyapp.viewmodel.FetchCharacters


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val characterAdapter: CharacterAdapter by lazy {
        CharacterAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FetchCharacters.fetchCharacters{ characterAdapter.submitList(it) }
        binding.rvRecycler
            .apply{
                layoutManager= LinearLayoutManager(this@MainActivity)
                adapter = characterAdapter}

    }
}
