package com.flamecode.itfest.ui.base

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.flamecode.itfest.R
import com.github.gcacace.signaturepad.views.SignaturePad


class SignatureFragment : Fragment() {

    private lateinit var clearBtn: Button
    private lateinit var saveBtn: Button
    private lateinit var sign: SignaturePad
    private lateinit var bitmap: Bitmap


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_signature, container, false)
        getData(view)
        saveBtn.isEnabled = false
        clearBtn.isEnabled = false
        sign.setPenColor(Color.RED)
        functionalities()
        return view
    }

    private fun functionalities() {
        sign.setOnSignedListener(object : SignaturePad.OnSignedListener {
            override fun onStartSigning() {
            }

            override fun onSigned() {
                if (sign.isEmpty.not()) {
                    saveBtn.isEnabled = true
                    clearBtn.isEnabled = true
                }
            }

            override fun onClear() {
                saveBtn.isEnabled = false
                clearBtn.isEnabled = false
            }
        })
        onClickListeners()
    }

    private fun onClickListeners() {
        clearBtn.setOnClickListener {
            sign.clear()
        }

        saveBtn.setOnClickListener {
            Toast.makeText(context, "Signature Saved", Toast.LENGTH_SHORT).show()

        }
    }


    private fun getData(view: View) {
        clearBtn = view.findViewById(R.id.retry)
        saveBtn = view.findViewById(R.id.save_signature)
        sign = view.findViewById(R.id.signature_pad)
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.reload)
    }
}