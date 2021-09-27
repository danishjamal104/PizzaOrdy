package com.github.danishjamal104.pizzaordy.data.remote.services

import com.github.danishjamal104.pizzaordy.data.model.Pizza
import retrofit2.http.GET

/**
 * Contains all the api endpoint.
 */
interface ApiService {

    @GET("/pizzas")
    suspend fun getPizza(): Pizza

}