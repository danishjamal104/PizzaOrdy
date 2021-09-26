package com.github.danishjamal104.pizzaordy.data.remote

import com.github.danishjamal104.pizzaordy.data.model.Pizza

interface NetworkDataSource {

    suspend fun getPizza(): Pizza
}