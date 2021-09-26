package com.github.danishjamal104.pizzaordy.data.remote

import com.github.danishjamal104.pizzaordy.data.mapper.pizzamapper.PizzaMapper
import com.github.danishjamal104.pizzaordy.data.model.Pizza
import com.github.danishjamal104.pizzaordy.data.remote.NetworkDataSource
import com.github.danishjamal104.pizzaordy.data.remote.services.pizza.PizzaService

class NetworkDataSourceImpl(
    private val pizzaService: PizzaService,
    val pizzaMapper: PizzaMapper
): NetworkDataSource {

    override suspend fun getPizza(): Pizza {
        return pizzaMapper.mapFromEntity(pizzaService.getPizza())
    }
}