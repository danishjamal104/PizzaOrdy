package com.github.danishjamal104.pizzaordy.data.repository

import com.github.danishjamal104.pizzaordy.data.helper.ServiceResult
import com.github.danishjamal104.pizzaordy.data.model.Pizza

interface HomeRepository {

    suspend fun getPizza(): ServiceResult<Pizza>
}