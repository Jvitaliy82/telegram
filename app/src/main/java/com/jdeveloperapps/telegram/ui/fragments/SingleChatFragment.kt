package com.jdeveloperapps.telegram.ui.fragments

import android.view.View
import com.jdeveloperapps.telegram.R
import com.jdeveloperapps.telegram.utilites.APP_ACTIVITY
import kotlinx.android.synthetic.main.activity_main.*

class SingleChatFragment(contact: Any?) : BaseFragment(R.layout.fragment_single_chat) {

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.toolbar_info.visibility = View.VISIBLE
    }

    override fun onPause() {
        super.onPause()
        APP_ACTIVITY.toolbar_info.visibility = View.GONE
    }

}