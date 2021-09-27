package com.github.danishjamal104.pizzaordy.data.helper

import java.lang.Exception

/**
 * Used a single class for returning a result from all the local/remote service
 * @author Danish Jamal [https://github.com/danishjamal104]
 * @param [T] Generic type which describes the type of data to be returned on success
 */
sealed class ServiceResult<out T> {

    object Loading: ServiceResult<Nothing>()
    data class Success<out T>(val data: T): ServiceResult<T>()
    data class Error(val exception: Exception): ServiceResult<Nothing>()

}
