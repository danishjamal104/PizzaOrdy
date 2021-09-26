package com.github.danishjamal104.pizzaordy.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.github.danishjamal104.pizzaordy.BuildConfig
import com.github.danishjamal104.pizzaordy.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "" + BuildConfig.DEBUG, Toast.LENGTH_LONG).show()
    }
}