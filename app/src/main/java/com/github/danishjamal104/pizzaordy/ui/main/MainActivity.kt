package com.github.danishjamal104.pizzaordy.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.github.danishjamal104.pizzaordy.BuildConfig
import com.github.danishjamal104.pizzaordy.R
import com.github.danishjamal104.pizzaordy.databinding.ActivityMainBinding
import com.github.danishjamal104.pizzaordy.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container)
        if (hostFragment is NavHostFragment) navController = hostFragment.navController
        val a = 1
    }
}