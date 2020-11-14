package com.flamecode.itfest.manager

import android.content.Context
import androidx.fragment.app.Fragment
import com.flamecode.itfest.utils.AppConstants

/**
 * This manager class have the role to transfer from a fragment to other easier
 * @author Iomava
 */
class FragmentManager(private val fragmentManager: Context?) {

    /**
     * This function add a new fragment
     * @param fragment the new fragment
     */
    fun addFragment(fragment: Fragment){

        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.add(AppConstants.containerLayout, fragment).addToBackStack(fragment.javaClass.canonicalName).commit()
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