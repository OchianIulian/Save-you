package com.flamecode.itfest.manager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.flamecode.itfest.utils.AppConstants

/**
 * This manager class have the role to transfer from a fragment to other easier
 * @author Iomava
 */
class FragmentManager(private val fragmentManager: FragmentManager) {

    /**
     * This function add a new fragment
     * @param fragment the new fragment
     */
    fun addFragment(fragment: Fragment){

        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.add(AppConstants.containerLayout, fragment)
            .addToBackStack(null).commit()
    }

    /**
     * This function have the scope to replace the last fragment with a new one
     * @param fragment the new fragment
     */
    fun replaceFragment(fragment: Fragment){

        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(AppConstants.containerLayout, fragment)
            .addToBackStack(null).commit()
    }
}