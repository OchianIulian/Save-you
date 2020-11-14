package com.flamecode.itfest.network

import android.content.Context
import android.widget.Toast
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response


/**
 * This class will be for getting the data for displaying in Analytics Fragment
 *
 *
 * @author Iomava
 */
class AnalyticsApi(private val context: Context) {

    companion object {

        const val TAG = "AnalyticsApi"
        const val baseUrl = "https://api.covid19api.com"
    }


    /**
     * A summary of new and total cases per country updated daily.
     *
     */
    fun getSummary() {

        val client = OkHttpClient().newBuilder()
            .build()
        val request: Request = Request.Builder()
            .url("https://api.covid19api.com/summary")
            .method("GET", null)
            .build()

        val thread = Thread {
            try {

                val response: Response = client.newCall(request).execute()
                if (response.isSuccessful) {

                    val message = response.message()
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                } else {

                    Toast.makeText(
                        context,
                        "Can not get last data about covid 19",
                        Toast.LENGTH_LONG
                    ).show()
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        thread.start()

    }
}