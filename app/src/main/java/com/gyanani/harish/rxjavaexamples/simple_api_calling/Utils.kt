package com.gyanani.harish.rxjavaexamples.simple_api_calling

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

object Utils {
    fun apiCalling(): String {
        val url = URL("https://jsonplaceholder.typicode.com/todos/1")
        val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
        urlConnection.requestMethod = "GET"
        return try {
            val inputStream: InputStream = urlConnection.inputStream
            readStream(inputStream)
        }catch (e: Exception) {
            e.message.toString()
        }
    }

    private fun readStream(inputStream: InputStream): String {
        var reader: BufferedReader? = null
        val response = StringBuffer()
        try {
            reader = BufferedReader(InputStreamReader(inputStream))
            var line: String? = ""
            while (reader.readLine().also { line = it } != null) {
                response.append(line)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (reader != null) {
                try {
                    reader.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return response.toString()
    }
}