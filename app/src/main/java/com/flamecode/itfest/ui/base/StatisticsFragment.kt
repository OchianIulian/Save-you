package com.flamecode.itfest.ui.base

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.flamecode.itfest.R
import com.flamecode.itfest.manager.FragmentManager
import com.flamecode.itfest.model.CountryData
import com.flamecode.itfest.model.GlobalData
import com.flamecode.itfest.ui.settings.SettingsFragment
import com.flamecode.itfest.utils.FormatNumber
import com.flamecode.itfest.utils.KTD
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.net.URL

/**
 * This fragment will have a role around the home, being the first fragment that
 * opens after SplashScreen. Here the local data related to the
 * Covid-19 state in the user's area will be displayed.
 *
 * @author Iomava
 */
class StatisticsFragment : Fragment() {

    private lateinit var settingsImageView: ImageView
    private lateinit var fragmentManager: FragmentManager
    private lateinit var globalAnalytics: GlobalData
    lateinit var countryData: CountryData
    lateinit var localCountryData: CountryData

    private lateinit var casesGlobalTextView: TextView
    private lateinit var deathGlobalTextView: TextView
    private lateinit var recoveredGlobalTextView: TextView

    private lateinit var activeGlobalTextView: TextView
    private lateinit var criticGlobalTextView: TextView
    private lateinit var casesPerMillionTextView: TextView
    private lateinit var deathPerMillionTextView: TextView

    private lateinit var todayCasesTextView: TextView
    private lateinit var totalTestsTextView: TextView
    private lateinit var activeLocalTextView: TextView

    private lateinit var userLocationTextView: TextView

    private var listAddress: List<Address>? = null

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

        val view = inflater.inflate(R.layout.fragment_statistics, container, false)

        fragmentManager = FragmentManager(this.getFragmentManager()!!)

        initViews(view)

        listAddress = getLastLocationKnow()

        GlobalScope.launch(context = Dispatchers.IO) {

            val response = URL("$baseUrl/all").readText()
            globalAnalytics = KTD().deserializeForGlobalData(response)

            val response2 = URL("$baseUrl/countries/World").readText()
            countryData = KTD().deserializeForCountryData(response2)

            GlobalScope.launch(context = Dispatchers.Main) {

                updateFirstMaterialCard(globalAnalytics)
                updateSecondMaterialCard(countryData)
            }
        }

        return view
    }

    override fun onResume() {
        super.onResume()

        if (ContextCompat.checkSelfPermission(
                context!!,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    activity!!,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                ActivityCompat.requestPermissions(
                    activity!!,
                    Array(1) { Manifest.permission.ACCESS_FINE_LOCATION }, 1
                )
            } else {
                ActivityCompat.requestPermissions(
                    activity!!,
                    Array(1) { Manifest.permission.ACCESS_FINE_LOCATION }, 1
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] === PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(
                            context!!,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

    private fun initViews(view: View) {

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
        userLocationTextView = view.findViewById(R.id.user_loc)

        todayCasesTextView = view.findViewById(R.id.todayCases)
        totalTestsTextView = view.findViewById(R.id.totalTests)
        activeLocalTextView = view.findViewById(R.id.active_local)
    }

    private fun updateThirdMaterialCard(countryData: CountryData) {

        MainScope().launch(context = Dispatchers.Main) {

            val format = FormatNumber()
            todayCasesTextView.text = format.format(countryData.todayCases.toString())
            totalTestsTextView.text = format.format(countryData.totalTests.toString())
            activeLocalTextView.text = format.format(countryData.active.toString())
        }
    }

    private fun updateSecondMaterialCard(countryData: CountryData) {

        MainScope().launch(context = Dispatchers.Main) {

            val format = FormatNumber()
            activeGlobalTextView.text = format.format(countryData.active.toString())
            criticGlobalTextView.text = format.format(countryData.critical.toString())
            casesPerMillionTextView.text = format.format(countryData.casesPerOneMillion.toString())
            deathPerMillionTextView.text = format.format(countryData.deathsPerOneMillion.toString())
        }
    }

    private fun updateFirstMaterialCard(globalData: GlobalData) {

        MainScope().launch(context = Dispatchers.Main) {

            val format = FormatNumber()
            casesGlobalTextView.text = format.format(globalData.cases.toString())
            deathGlobalTextView.text = format.format(globalData.deaths.toString())
            recoveredGlobalTextView.text = format.format(globalData.recovered.toString())
        }
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    var list: List<Address>? = null

    @SuppressLint("SetTextI18n")
    private fun getLastLocationKnow(): List<Address>? {


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context!!)

        if (ActivityCompat.checkSelfPermission(
                context!!,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context!!,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

        }

        fusedLocationClient.requestLocationUpdates(
            LocationRequest(), LocationCallback(),
            Looper.getMainLooper()
        )


        if (ActivityCompat.checkSelfPermission(
                context!!,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context!!,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                list = getCountryName(location!!.latitude, location.longitude)
                userLocationTextView.text = list!![0].locality + ", " + list!![0].countryName

                GlobalScope.launch(context = Dispatchers.IO) {

                    val response3 = URL("$baseUrl/countries/${list!![0].countryName}").readText()
                    localCountryData = KTD().deserializeForCountryData(response3)

                    GlobalScope.launch(context = Dispatchers.Main) {

                        updateThirdMaterialCard(localCountryData)
                    }
                }

            }.addOnCanceledListener {
                Toast.makeText(context, "Error getting the location", Toast.LENGTH_LONG).show()
            }



        return list
    }

    private fun getCountryName(lat: Double, long: Double): List<Address> {

        val geocoder = Geocoder(context)
        return geocoder.getFromLocation(lat, long, 1)
    }
}