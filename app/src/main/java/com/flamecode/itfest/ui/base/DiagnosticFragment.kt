package com.flamecode.itfest.ui.base

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.fragment.app.FragmentManager
import com.flamecode.itfest.R
import com.flamecode.itfest.ui.base.Diagnostics.DonnateFragment
import com.flamecode.itfest.ui.base.Diagnostics.FirstStadeFragment
import com.flamecode.itfest.ui.base.Diagnostics.SecondStadeFragment
import com.flamecode.itfest.ui.base.Diagnostics.ThirdStadeFragment
import com.flamecode.itfest.utils.AppConstants

class DiagnosticFragment : Fragment() {

    private lateinit var firstStade : RelativeLayout
    private lateinit var secondStade : RelativeLayout
    private lateinit var thirdStade : RelativeLayout
    private lateinit var donate : RelativeLayout
    private lateinit var gobackBtn : ImageView

    private  var coronaCounter: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_diagnostic, container, false)
        getData(view)
        onClicklisteners()
        return view
    }

    private fun onClicklisteners() {
        firstStade.setOnClickListener {
            fragmentManager!!.beginTransaction().replace(AppConstants.containerLayout, FirstStadeFragment())
        }
        secondStade.setOnClickListener {
            fragmentManager!!.beginTransaction().replace(AppConstants.containerLayout, SecondStadeFragment())
        }
        thirdStade.setOnClickListener {
            fragmentManager!!.beginTransaction().replace(AppConstants.containerLayout, ThirdStadeFragment())
            coronaCounter = true
        }
        donate.setOnClickListener {
            fragmentManager!!.beginTransaction().replace(AppConstants.containerLayout, DonnateFragment())
        }
    }

    private fun getData(view: View) {
        firstStade = view.findViewById(R.id.first_stade)
        secondStade  = view.findViewById(R.id.second_stade)
        thirdStade = view.findViewById(R.id.third_stade)
        donate = view.findViewById(R.id.donnate)
        gobackBtn = view.findViewById(R.id.goBack)
    }

}