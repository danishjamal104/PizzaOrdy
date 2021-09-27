package com.github.danishjamal104.pizzaordy.data.remote

import com.github.danishjamal104.pizzaordy.data.mapper.pizzamapper.PizzaMapper
import com.github.danishjamal104.pizzaordy.data.model.Pizza
import com.github.danishjamal104.pizzaordy.data.remote.NetworkDataSource
import com.github.danishjamal104.pizzaordy.data.remote.services.pizza.PizzaService

/**
 * Implementation of the [NetworkDataSource]
 * @author Danish Jamal [https://github.com/danishjamal104]
 * @param [pizzaService] takes pizzaService instance for making pizza related service request
 * @param [pizzaMapper] responsible for conversion of data from entity to domain
 */
class NetworkDataSourceImpl(
    private val pizzaService: PizzaService,
    private val pizzaMapper: PizzaMapper
): NetworkDataSource {

    override suspend fun getPizza(): Pizza {
        return pizzaMapper.mapFromEntity(pizzaService.getPizza())
    }
}