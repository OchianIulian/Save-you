package com.flamecode.itfest.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.flamecode.itfest.R
import com.flamecode.itfest.manager.FragmentManager
import com.flamecode.itfest.ui.base.DiagnosticFragment

class SettingsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.slide_right)
    }

    private lateinit var switchOnline: Switch
    private lateinit var switchDark: Switch
    private lateinit var diagnosticTextView : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        getData(view)

        switchOnline.setOnClickListener {

            if (switchOnline.isChecked) {

                switchOnline.text = "Online"
                //TODO: se trece la modul online
            } else {
                switchOnline.text = "Offline"
                //TODO: se trece la modul offline
            }
        }

        switchDark.setOnClickListener {

            if (switchDark.isChecked) {

                switchDark.text = "Dark mode"
                //TODO: se trece la modul dark
            } else {
                switchDark.text = "LightMode"
                //TODO: se trece la modul light
            }
        }
        return view
    }

    private fun getData(view: View) {

        switchOnline = view.findViewById(R.id.switch_online)
        switchDark = view.findViewById(R.id.switch_dark)
        diagnosticTextView = view.findViewById(R.id.diagnosticTextView)

        diagnosticTextView.setOnClickListener {

            FragmentManager(childFragmentManager).addFragment(DiagnosticFragment())
        }
    }
}