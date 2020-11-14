package com.flamecode.itfest.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils.loadAnimation
import android.widget.ImageView
import android.widget.TextView
import com.flamecode.itfest.R
import com.flamecode.itfest.manager.FragmentManager

class SplashScreen : Fragment() {

    lateinit var logo : ImageView
    lateinit var appName: TextView
    lateinit var animation : Animation

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_spalsh_screeen, container, false)

        getData(view)
        startAnimation()
        return view
    }

    private fun startAnimation() {

        logo.startAnimation(animation)
        val alphaAnimation = AlphaAnimation(0.0f, 1.0f)
        alphaAnimation.duration = 2000
        appName.startAnimation(alphaAnimation)

        animation.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {

                val fragmentManager = FragmentManager(this@SplashScreen.fragmentManager!!)
                fragmentManager.replaceFragment(StatisticsFragment())
            }

            override fun onAnimationRepeat(animation: Animation?) {}

        })
    }

    private fun getData(view: View) {

        logo = view.findViewById(R.id.logo)
        appName = view.findViewById(R.id.app_name)
        animation = loadAnimation(context, R.anim.pulse)
    }
}