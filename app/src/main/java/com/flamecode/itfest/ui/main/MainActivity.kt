package com.flamecode.itfest.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayoutStates
import com.flamecode.itfest.R
import com.flamecode.itfest.manager.FragmentManager
import com.flamecode.itfest.ui.base.SplashScreen

/**
 * This will parent for all the fragments
 * @author Iomava
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startLoadingScreen()
    }

    private fun startLoadingScreen() {

        val fragmentManager = FragmentManager(supportFragmentManager)
        fragmentManager.addFragment(SplashScreen())
    }
}