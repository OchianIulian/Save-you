package com.flamecode.itfest.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.transition.TransitionInflater
import com.flamecode.itfest.R
import com.flamecode.itfest.manager.FragmentManager
import com.flamecode.itfest.network.AnalyticsApi
import com.flamecode.itfest.ui.settings.SettingsFragment

/**
 * This fragment will have a role around the home, being the first fragment that
 * opens after SplashScreen. Here the local data related to the
 * Covid-19 state in the user's area will be displayed.
 *
 * @author Iomava
 */
class StatisticsFragment : Fragment() {

    lateinit var settingsImageView : ImageView
    lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val analyticsApi =  AnalyticsApi(context!!)
        analyticsApi.getSummary()

        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.fade)
        exitTransition = inflater.inflateTransition(R.transition.slide_right)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_statistics, container, false)

        fragmentManager = FragmentManager(this.getFragmentManager()!!)

        initViews(view)

        return view
    }

    private fun initViews(view : View) {

        settingsImageView = view.findViewById(R.id.settings)
        settingsImageView.setOnClickListener {
            fragmentManager.replaceFragment(SettingsFragment())
        }
    }
}