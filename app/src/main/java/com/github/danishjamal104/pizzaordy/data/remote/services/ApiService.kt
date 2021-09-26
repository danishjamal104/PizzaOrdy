package com.github.danishjamal104.pizzaordy.data.remote.services

import com.github.danishjamal104.pizzaordy.data.model.Pizza
import retrofit2.http.GET

interface ApiService {

    @GET("/pizzas")
    suspend fun getPizza(): Pizza

}