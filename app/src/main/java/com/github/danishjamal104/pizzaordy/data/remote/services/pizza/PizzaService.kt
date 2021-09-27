package com.github.danishjamal104.pizzaordy.data.remote.services.pizza

import com.github.danishjamal104.pizzaordy.data.model.Pizza

/**
 * A single source of truth for all the pizza related remote services
 * @author Danish Jamal [https://github.com/danishjamal104]
 */
interface PizzaService {

    /**
     * fetches pizza from the [ApiService]
     */
    suspend fun getPizza(): Pizza
}