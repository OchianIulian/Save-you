package com.flamecode.itfest.utils

import com.flamecode.itfest.model.CountryData
import com.flamecode.itfest.model.GlobalData
import java.lang.StringBuilder

/**
 * This class is for deserialization, for very simple one
 * It was created to save memory and space for apk
 *
 *
 */
class KTD {

    fun deserializeForGlobalData(data : String) : GlobalData {

        var valuesInClass = -1
        val array = IntArray(3)
        var readyForInput = false

        data.forEach {
            if (it.compareTo(':') == 0) {
                readyForInput = true
                valuesInClass++
            }
            else if(it.compareTo(',') == 0){
                readyForInput = false
            }
            else {
                if (readyForInput){
                    if (it.isDigit()){
                        array[valuesInClass] = array[valuesInClass] * 10 + (it - '0')
                    }
                }
            }
        }

        return GlobalData(array[0], array[1], array[2])
    }

    fun deserializeForCountryData(data : String) : CountryData {

        var valuesInClass = -1
        val array = IntArray(12)
        val stringBuilder = StringBuilder()
        var readyForInput = false

        data.forEach {
            if (it.compareTo(':') == 0) {
                readyForInput = true
                valuesInClass++
            }
            else if(it.compareTo(',') == 0){
                readyForInput = false
            }
            else {
                if (readyForInput){
                    if (it.isDigit()){
                        array[valuesInClass] = array[valuesInClass] * 10 + (it - '0')
                    } else if(it.isLetter()) {
                        stringBuilder.append(it)
                    }
                }
            }
        }

        return CountryData(String(stringBuilder), array[1], array[2], array[3], array[4], array[5],
            array[6], array[7], array[8], array[9], array[10], array[11])
    }
}