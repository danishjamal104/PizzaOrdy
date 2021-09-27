package com.github.danishjamal104.pizzaordy.data.remote.services.pizza

import com.github.danishjamal104.pizzaordy.data.model.Pizza
import com.github.danishjamal104.pizzaordy.data.remote.services.ApiService

/**
 * Implementation of the [PizzaService]
 * @author Danish Jamal [https://github.com/danishjamal104]
 */
class PizzaServiceImpl(
    private val apiService: ApiService
    ): PizzaService {

    override suspend fun getPizza(): Pizza {
       return apiService.getPizza()
    }
}