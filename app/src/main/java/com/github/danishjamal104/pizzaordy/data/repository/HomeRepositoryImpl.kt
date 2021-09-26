package com.github.danishjamal104.pizzaordy.data.repository

import com.github.danishjamal104.pizzaordy.data.helper.ServiceResult
import com.github.danishjamal104.pizzaordy.data.model.Pizza
import com.github.danishjamal104.pizzaordy.data.remote.NetworkDataSource
import java.lang.Exception

class HomeRepositoryImpl(
    private val networkDataSource: NetworkDataSource
): HomeRepository {

    override suspend fun getPizza(): ServiceResult<Pizza> {
        return try {
            val result = networkDataSource.getPizza()
            ServiceResult.Success(result)
        } catch (e: Exception) {
            ServiceResult.Error("" + e.localizedMessage)
        }
    }
}