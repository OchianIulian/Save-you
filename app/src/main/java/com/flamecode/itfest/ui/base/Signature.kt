package com.flamecode.itfest.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.flamecode.itfest.R
import com.kyanogen.signatureview.SignatureView


class Signature : Fragment() {

    private lateinit var clearBtn : Button
    private lateinit var saveBtn : Button
    private lateinit var signature: SignatureView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_signature, container, false)
        getData(view)
        functionalities()
        return view
    }

    private fun functionalities() {
        onClickListeners()
    }

    private fun onClickListeners() {
        clearBtn.setOnClickListener{
            clearBoard()
        }
        saveBtn.setOnClickListener{

        }
    }

    private fun getData(view: View) {
        clearBtn = view.findViewById(R.id.retry)
        saveBtn = view.findViewById(R.id.save_signature)
        signature = view.findViewById(R.id.signature_view)
    }

    private fun clearBoard(){
        signature.clearCanvas()
    }


}