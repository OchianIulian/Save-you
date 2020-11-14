package com.flamecode.itfest.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flamecode.itfest.R

/**
 * This fragment will have a role around the home, being the first fragment that
 * opens after SplashScreen. Here the local data related to the
 * Covid-19 state in the user's area will be displayed.
 */
class StatisticsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_statistics, container, false)



        return view
    }
}