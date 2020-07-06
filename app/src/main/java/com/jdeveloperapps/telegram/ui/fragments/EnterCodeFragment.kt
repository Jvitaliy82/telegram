package com.jdeveloperapps.telegram.ui.fragments

import androidx.fragment.app.Fragment
import com.google.firebase.auth.PhoneAuthProvider
import com.jdeveloperapps.telegram.MainActivity
import com.jdeveloperapps.telegram.R
import com.jdeveloperapps.telegram.activities.RegisterActivity
import com.jdeveloperapps.telegram.utilites.AUTH
import com.jdeveloperapps.telegram.utilites.AppTextWatcher
import com.jdeveloperapps.telegram.utilites.replaceActivity
import com.jdeveloperapps.telegram.utilites.showToast
import kotlinx.android.synthetic.main.fragment_enter_code.*

class EnterCodeFragment(val phoneNumber: String, val id: String) : Fragment(R.layout.fragment_enter_code) {

    override fun onStart() {
        super.onStart()
        (activity as RegisterActivity).title = phoneNumber
        register_input_code.addTextChangedListener(AppTextWatcher{
                val string = register_input_code.text.toString()
                if (string.length == 6) {
                    enterCode()
                }
        })
    }

    private fun enterCode() {
        val code = register_input_code.text.toString()
        val credential = PhoneAuthProvider.getCredential(id, code)
        AUTH.signInWithCredential(credential).addOnCompleteListener{
            if (it.isSuccessful) {
                showToast("Welcome")
                (activity as RegisterActivity).replaceActivity(MainActivity())
            } else {
                showToast(it.exception.toString())
            }
        }
    }
}