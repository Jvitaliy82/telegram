package com.jdeveloperapps.telegram.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.jdeveloperapps.telegram.MainActivity
import com.jdeveloperapps.telegram.R
import com.jdeveloperapps.telegram.utilites.*
import kotlinx.android.synthetic.main.fragment_change_name.*

class ChangeNameFragment : BaseFragment(R.layout.fragment_change_name) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        val fullNameList = USER.fullname.split(" ")
        settings_input_name.setText(fullNameList[0])
        settings_input_surname.setText(fullNameList[1])
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (activity as MainActivity).menuInflater.inflate(R.menu.settings_confirm_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.settings_confirm_change -> changeName()
        }
        return true
    }

    private fun changeName() {
        val name = settings_input_name.text.toString()
        val surname = settings_input_surname.text.toString()
        if (name.isEmpty()) {
            showToast(getString(R.string.settings_toast_name_is_empty))
        } else {
            val fullName = "$name $surname"
            REF_DATABASE_ROOT.child(NODE_USERS).child(UID).child(CHILD_FULLNAME).setValue(fullName)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        showToast(getString(R.string.toast_data_update))
                        USER.fullname = fullName
                        fragmentManager?.popBackStack()
                    }
                }
        }
    }
}