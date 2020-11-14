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
import androidx.transition.TransitionInflater
import com.flamecode.itfest.R
import com.flamecode.itfest.manager.FragmentManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class SplashScreen : Fragment() {

    lateinit var logo : ImageView
    lateinit var appName: TextView
    lateinit var animation : Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_spalsh_screeen, container, false)

        getData(view)
        startAnimation()
        return view
    }

    private fun startAnimation() {


        val alphaAnimation = AlphaAnimation(0.0f, 1.0f)
        alphaAnimation.duration = 3000

        MainScope().launch {

            appName.startAnimation(alphaAnimation)
            logo.startAnimation(animation)
        }

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