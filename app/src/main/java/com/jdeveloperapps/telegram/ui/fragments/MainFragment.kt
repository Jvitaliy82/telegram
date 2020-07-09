package com.jdeveloperapps.telegram.ui.fragments

import androidx.fragment.app.Fragment
import com.jdeveloperapps.telegram.R
import com.jdeveloperapps.telegram.utilites.APP_ACTIVITY
import com.jdeveloperapps.telegram.utilites.hideKeyboard

class MainFragment : Fragment(R.layout.fragment_chats) {

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = getString(R.string.chat_fragment_title)
        APP_ACTIVITY.mAppDrawer.enableDrawer()
        hideKeyboard()
    }

}