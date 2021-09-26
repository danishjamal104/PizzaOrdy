package com.github.danishjamal104.pizzaordy.data.helper

import java.lang.Exception

sealed class ServiceResult<out T> {

    object Loading: ServiceResult<Nothing>()
    data class Success<out T>(val data: T): ServiceResult<T>()
    data class Error(val exception: Exception): ServiceResult<Nothing>()

}
