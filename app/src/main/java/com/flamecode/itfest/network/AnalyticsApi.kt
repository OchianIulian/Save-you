package com.flamecode.itfest.network

import android.content.Context
import android.util.Log
import java.lang.Error
import java.net.URL

/**
 * This class will be for getting the data for displaying in Analytics Fragment
 *
 * @author Iomava
 */

class AnalyticsApi(private val context: Context) {

    companion object {

        const val TAG = "AnalyticsApi"
        const val baseUrl = "https://coronavirus-19-api.herokuapp.com"
    }

    /**
     * A summary of new and total cases per country updated daily.
     *
     */
    fun getSummaryGlobal(): String? {

        var response : String? = null

        val thread = Thread {
            try {
               response = URL("$baseUrl/all").readText()
            } catch (error : Error) {
                Log.d(TAG, error.message!!)
            }

        }
        thread.start()

        return response
    }
}

