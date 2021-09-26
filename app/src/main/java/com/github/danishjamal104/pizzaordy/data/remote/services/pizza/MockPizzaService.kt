package com.github.danishjamal104.pizzaordy.data.remote.services.pizza

import com.github.danishjamal104.pizzaordy.data.model.Pizza
import com.github.danishjamal104.pizzaordy.utils.AppConstants
import com.google.gson.Gson

class MockPizzaService(
    private val gson: Gson
): PizzaService {

    override suspend fun getPizza(): Pizza {
        return gson.fromJson(AppConstants.DEFAULT_PIZZA_JSON, Pizza::class.java)
    }
}