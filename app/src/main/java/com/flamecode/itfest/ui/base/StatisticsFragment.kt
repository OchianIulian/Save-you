package com.flamecode.itfest.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.transition.TransitionInflater
import com.flamecode.itfest.R
import com.flamecode.itfest.manager.FragmentManager
import com.flamecode.itfest.model.CountryData
import com.flamecode.itfest.model.GlobalData
import com.flamecode.itfest.network.AnalyticsApi
import com.flamecode.itfest.ui.settings.SettingsFragment
import com.flamecode.itfest.utils.FormatNumber
import com.flamecode.itfest.utils.KTD
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import java.net.URL

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
    lateinit var globalAnalytics : GlobalData
    lateinit var countryData: CountryData

    lateinit var casesGlobalTextView : TextView
    lateinit var deathGlobalTextView : TextView
    lateinit var recoveredGlobalTextView : TextView

    lateinit var activeGlobalTextView : TextView
    lateinit var criticGlobalTextView : TextView
    lateinit var casesPerMillionTextView : TextView
    lateinit var deathPerMillionTextView : TextView


    companion object {

        const val baseUrl = "https://coronavirus-19-api.herokuapp.com"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setting animations on enter and exit
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.fade)
        exitTransition = inflater.inflateTransition(R.transition.slide_right)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_statistics, container, false)

        GlobalScope.launch( context = Dispatchers.IO) {

            val response = URL("$baseUrl/all").readText()
            globalAnalytics = KTD().deserializeForGlobalData(response)

            val response2 = URL("$baseUrl/countries/World").readText()
            countryData = KTD().deserializeForCountryData(response2)

            GlobalScope.launch( context = Dispatchers.Main) {

                updateFirstMaterialCard(globalAnalytics)
                updateSecondMaterialCard(countryData)
            }
        }

        fragmentManager = FragmentManager(this.getFragmentManager()!!)

        initViews(view)

        return view
    }

    private fun initViews(view : View) {

        settingsImageView = view.findViewById(R.id.settings)
        settingsImageView.setOnClickListener {
            fragmentManager.replaceFragment(SettingsFragment())
        }

        casesGlobalTextView = view.findViewById(R.id.casesGlobal)
        deathGlobalTextView = view.findViewById(R.id.deathGlobal)
        recoveredGlobalTextView = view.findViewById(R.id.recoveredGlobal)

        activeGlobalTextView = view.findViewById(R.id.activeGlobal)
        criticGlobalTextView = view.findViewById(R.id.criticGlobal)
        casesPerMillionTextView = view.findViewById(R.id.casesPerMillion)
        deathPerMillionTextView = view.findViewById(R.id.deathPerMillion)
    }

    private fun updateSecondMaterialCard(countryData: CountryData) {

        MainScope().launch(context = Dispatchers.Main){

            val format = FormatNumber()
            activeGlobalTextView.text = format.format(countryData.active.toString())
            criticGlobalTextView.text =  format.format(countryData.critical.toString())
            casesPerMillionTextView.text = format.format(countryData.casesPerOneMillion.toString())
            deathPerMillionTextView.text = format.format(countryData.deathsPerOneMillion.toString())
        }
    }

    private fun updateFirstMaterialCard(globalData: GlobalData) {

        MainScope().launch(context = Dispatchers.Main){

            val format = FormatNumber()
            casesGlobalTextView.text = format.format(globalData.cases.toString())
            deathGlobalTextView.text =  format.format(globalData.deaths.toString())
            recoveredGlobalTextView.text = format.format(globalData.recovered.toString())
        }
    }
}