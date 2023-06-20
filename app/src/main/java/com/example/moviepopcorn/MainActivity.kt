package com.example.moviepopcorn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.moviepopcorn.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

//android entry point untuk menandakan depedensi injection
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
//  variabel nav controller
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//      digunakan view binding untuk memudahkan interaksi dengan tampilan di layout
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//      variabel untuk navigasi host fragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.findNavController()

//      variabel dibawah digunakan untuk membuat navigation bottom
//      yang terdiri dari 2 menu yaitu nav movie dan nav favorite
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.nav_movie, R.id.nav_favorite
        ).build()
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.apply {
            navBottom.setupWithNavController(navController)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}