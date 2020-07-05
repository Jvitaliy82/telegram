package com.jdeveloperapps.telegram.ui.fragments

import androidx.fragment.app.Fragment
import com.jdeveloperapps.telegram.R
import com.jdeveloperapps.telegram.utilites.AppTextWatcher
import com.jdeveloperapps.telegram.utilites.showToast
import kotlinx.android.synthetic.main.fragment_enter_code.*

class EnterCodeFragment : Fragment(R.layout.fragment_enter_code) {

    override fun onStart() {
        super.onStart()
        register_input_code.addTextChangedListener(AppTextWatcher{
                val string = register_input_code.text.toString()
                if (string.length == 6) {
                    verificateCode()
                }
        })
    }

    fun verificateCode() {
        showToast("Ok")
    }
}