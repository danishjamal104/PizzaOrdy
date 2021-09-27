package com.github.danishjamal104.pizzaordy.utils

import android.content.Context
import com.github.danishjamal104.pizzaordy.R
import com.google.gson.stream.JsonReader
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

fun Context.getJsonFromAssets(fileName: String): String {

    val jsonString: String
    jsonString = try {
        val `is`: InputStream = this.getAssets().open(fileName)
        val size: Int = `is`.available()
        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()
        String(buffer)
    } catch (e: IOException) {
        e.printStackTrace()
        return ""
    }

    return jsonString
}

fun Double.inRupeesFormat(context: Context): String {
    return "${context.getString(R.string.rupee_symbol)} $this"
}