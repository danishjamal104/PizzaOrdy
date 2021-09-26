package com.github.danishjamal104.pizzaordy.data.remote.services.pizza

import com.github.danishjamal104.pizzaordy.data.model.Pizza
import com.github.danishjamal104.pizzaordy.data.remote.services.ApiService

class PizzaServiceImpl(
    val apiService: ApiService
    ): PizzaService {

    override suspend fun getPizza(): Pizza {
       return apiService.getPizza()
    }
}