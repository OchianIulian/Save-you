package com.flamecode.itfest.utils

import java.lang.StringBuilder

/**
 *  This class is formatting the numbers in a easy way for read
 *
 *
 * @author Iomava
 */
class FormatNumber {

    fun format(s : String) : String {

        var sir = s
        val stringBuilder = StringBuilder()
        if (s.length > 3){
            sir = s.reversed()
            var i = 0
            sir.forEach {
                stringBuilder.append(it)
                if ( i == 2) {
                    if (stringBuilder.length <= s.length)
                        stringBuilder.append(',')
                    i = -1
                }
                ++i
            }



            return String(stringBuilder).reversed()
        }
        return s
    }
}