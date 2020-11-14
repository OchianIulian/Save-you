package com.flamecode.itfest.database.model

import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng

class VitalDatabase {

    companion object {

        val listHospitals: List<VitalLocation> =
            listOf(
                VitalLocation(
                    "Spitalul de Obstetrica - Ginecologie \"Buna Vestire\"",
                    Location(LatLng(45.444482, 28.043744)),
                    BitmapDescriptorFactory.HUE_BLUE
                ),
                VitalLocation(
                    "Spitalul Municipal Oneşti",
                    Location(LatLng(46.253611, 26.764945)),
                    BitmapDescriptorFactory.HUE_BLUE
                ),
                VitalLocation(
                    "Spitalul Municipal Oneşti",
                    Location(LatLng(46.253611, 26.764945)),
                    BitmapDescriptorFactory.HUE_BLUE
                ),
                VitalLocation(
                    text = "Spitalul Clinic Județean de Urgență Brașov",
                    Location(LatLng(45.648721, 25.620720)),
                    BitmapDescriptorFactory.HUE_BLUE
                ),
                VitalLocation(
                    text = "Spitalul Clinic Judeţean de Urgenţă Sibiu",
                    Location(LatLng(45.794414, 24.155781)),
                    BitmapDescriptorFactory.HUE_BLUE
                ),
                VitalLocation(
                    text = "Victor Babeș Hospital",
                    Location(LatLng(44.427204, 26.139505)),
                    BitmapDescriptorFactory.HUE_BLUE
                ),
                VitalLocation(
                    text = "Institutul Național de Expertiză Medicală și Recuperare a Capacității de Muncă",
                    Location(LatLng(44.425397, 26.071297)),
                    BitmapDescriptorFactory.HUE_BLUE
                )
            )

    }
}