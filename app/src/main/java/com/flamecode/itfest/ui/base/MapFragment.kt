package com.flamecode.itfest.ui.base

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Camera
import android.location.Location
import android.os.Bundle
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.flamecode.itfest.R
import com.flamecode.itfest.utils.KTD
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_map, container, false)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return view
    }

    override fun onMapReady(googleMap: GoogleMap) {

        googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context!!)

        if (ActivityCompat.checkSelfPermission(
                context!!,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context!!,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->

                val userLocation = LatLng(location!!.latitude, location.longitude)
                googleMap.run {
                    animateCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 0.5f))

                    addMarker(
                                MarkerOptions()
                                    .position(userLocation)
                                    .title("Your Location")
                            )
                }


            }.addOnCanceledListener {
                Toast.makeText(context, "Error getting the location", Toast.LENGTH_LONG).show()
            }



    }


}