package com.github.danishjamal104.pizzaordy.data.remote.interceptor

import android.content.Context
import com.github.danishjamal104.pizzaordy.R
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor
constructor(private val context: Context) : Interceptor, InternetCheck(context) {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!hasActiveInternetConnection()) {
            throw Exception(context.getString(R.string.no_internet_message))
        }
        return chain.proceed(chain.request())
    }
}