package com.flamecode.itfest.ui.base

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.flamecode.itfest.R
import com.github.gcacace.signaturepad.views.SignaturePad
import com.kyanogen.signatureview.SignatureView
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.net.URI
import java.security.SignatureSpi


class SignatureFragment : Fragment() {

    private lateinit var clearBtn : Button
    private lateinit var saveBtn : Button
    private lateinit var sign: SignaturePad
   // private lateinit var bitmap: Bitmap


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
        clearBtn.setOnClickListener(){
        }
    }

    private fun getData(view: View) {
        clearBtn = view.findViewById(R.id.retry)
        saveBtn = view.findViewById(R.id.save_signature)
        sign = view.findViewById(R.id.signature_pad)
    }


}