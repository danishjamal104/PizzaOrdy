package com.github.danishjamal104.pizzaordy.data.remote.services.pizza

import com.github.danishjamal104.pizzaordy.data.model.Pizza

interface PizzaService {

    suspend fun getPizza(): Pizza
}