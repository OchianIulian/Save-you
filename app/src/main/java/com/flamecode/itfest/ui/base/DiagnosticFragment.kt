package com.flamecode.itfest.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import com.flamecode.itfest.R
import com.flamecode.itfest.ui.base.Diagnostics.DonnateFragment
import com.flamecode.itfest.ui.base.Diagnostics.FirstStadeFragment
import com.flamecode.itfest.ui.base.Diagnostics.SecondStadeFragment
import com.flamecode.itfest.ui.base.Diagnostics.ThirdStadeFragment

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
            com.flamecode.itfest.manager.FragmentManager(fragmentManager).addFragment(FirstStadeFragment())
        }
        secondStade.setOnClickListener {
            // com.flamecode.itfest.manager.FragmentManager(fragmentManager).addFragment(SecondStadeFragment())
            Toast.makeText(context, "In development...", Toast.LENGTH_LONG).show()
        }
        thirdStade.setOnClickListener {
            com.flamecode.itfest.manager.FragmentManager(fragmentManager).addFragment(ThirdStadeFragment())
            coronaCounter = true
        }
        donate.setOnClickListener {
            com.flamecode.itfest.manager.FragmentManager(fragmentManager).addFragment(DonnateFragment())
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