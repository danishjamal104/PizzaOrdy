package com.github.danishjamal104.pizzaordy.data.remote.interceptor

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

open class InternetCheck(@ApplicationContext val applicationContext: Context) {

    private val TAG = "internetcheck";

    @SuppressLint("ServiceCast")
    private fun isConnected(): Boolean {
        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return connectivityManager.activeNetwork != null
        } else {
            val activeNetwork = connectivityManager.activeNetworkInfo
            if(activeNetwork != null) {
                return activeNetwork.type == ConnectivityManager.TYPE_WIFI ||
                        activeNetwork.type == ConnectivityManager.TYPE_MOBILE
            }
            return false
        }
        //return networkInfo != null
    }

    fun hasActiveInternetConnection(): Boolean {
        if (isConnected()) {
            try {
                val urlc: HttpURLConnection =
                    URL("https://clients3.google.com/generate_204").openConnection() as HttpURLConnection
                urlc.setRequestProperty("User-Agent", "Android")
                urlc.setRequestProperty("Connection", "close")
                urlc.connectTimeout = 1500
                urlc.connect()
                return (urlc.responseCode == 204 && urlc.contentLength == 0)
            } catch (e: IOException) {
                Log.e(TAG, "Error checking internet connection")
            }
        } else {
            Log.e(TAG, "No network available!")
        }
        return false
    }
}