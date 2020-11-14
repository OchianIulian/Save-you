package com.flamecode.itfest.ui.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Switch
import androidx.transition.TransitionInflater
import com.flamecode.itfest.R

class SettingsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.slide_right)
    }

    private lateinit var switch_online : Switch
    private lateinit var switch_dark: Switch

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val view =inflater.inflate(R.layout.fragment_settings, container, false)
        getData(view)

        switch_online.setOnClickListener{
            if(switch_online.isChecked){
                switch_online.text = "Online"
                //TODO: se trece la modul online
            } else {
                switch_online.text = "Offline"
                //TODO: se trece la modul offline
            }
        }

        switch_dark.setOnClickListener{
            if(switch_dark.isChecked){
                switch_dark.text = "Dark mode"
                //TODO: se trece la modul dark
            } else {
                switch_dark.text = "LightMode"
                //TODO: se trece la modul light
            }
        }
        return view
    }

    private fun getData(view: View) {
        switch_online = view.findViewById(R.id.switch_online)
        switch_dark = view.findViewById(R.id.switch_dark)
    }
}