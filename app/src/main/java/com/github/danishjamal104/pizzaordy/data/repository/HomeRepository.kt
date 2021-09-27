package com.github.danishjamal104.pizzaordy.data.repository

import com.github.danishjamal104.pizzaordy.data.helper.ServiceResult
import com.github.danishjamal104.pizzaordy.data.model.Pizza

/**
 * Repository class for Home page related query
 * @author Danish Jamal [https://github.com/danishjamal104]
 */
interface HomeRepository {

    /**
     * fetches pizza from data sources
     */
    suspend fun getPizza(): ServiceResult<Pizza>
}