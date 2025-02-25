package com.example.rickandmortyapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(ActivityMainBinding.inflate(layoutInflater)) {
            setContentView(root)
            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
            val navController = navHostFragment.navController
            val graphInflater = navHostFragment.navController.navInflater
            val navGraph = graphInflater.inflate(R.navigation.nav_graph_main)
            navGraph.setStartDestination(R.id.characterFragment)
            val appBarConfiguration = AppBarConfiguration(navGraph)
            setupActionBarWithNavController(navController, appBarConfiguration)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}
