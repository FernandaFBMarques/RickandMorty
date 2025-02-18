package com.example.rickandmortyapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapp.viewmodel.CharacterAdapter
import com.example.rickandmortyapp.databinding.ActivityMainBinding
import com.example.rickandmortyapp.viewmodel.FetchCharacters
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import com.example.rickandmortyapp.R

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentPage = 1
    private val characterAdapter: CharacterAdapter by lazy {
        CharacterAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)

        initUI()

    }
    fun initUI(){

        FetchCharacters.fetchCharacters(currentPage){ characterAdapter.submitList(it) }

        binding.apply {
            charactersView.setOnScrollChangeListener(NestedScrollView.
            OnScrollChangeListener { v, _, scrollY, _, oldScrollY ->
                Log.i("yaya", "is here? scrollY: $scrollY oldScrollY: $oldScrollY")
                if((scrollY > oldScrollY)&&(!v.canScrollVertically(1))){

                        currentPage++
                        FetchCharacters.fetchCharacters(currentPage){ characterAdapter.submitList(it) }

                }

            })
        }

        binding
            .apply {
                rvRecycler.layoutManager= LinearLayoutManager(this@MainActivity)
                rvRecycler.adapter = characterAdapter
            }

    }

}
