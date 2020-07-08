package com.jdeveloperapps.telegram.ui.fragments

import com.jdeveloperapps.telegram.R
import com.jdeveloperapps.telegram.database.USER
import com.jdeveloperapps.telegram.database.setBioToDatabase
import kotlinx.android.synthetic.main.fragment_change_bio.*

class ChangeBioFragment : BaseChangeFragment(R.layout.fragment_change_bio) {

    override fun onResume() {
        super.onResume()
        settings_input_bio.setText(USER.bio)
    }

    override fun change() {
        super.change()
        val newBio = settings_input_bio.text.toString()
        setBioToDatabase(newBio)
    }


}