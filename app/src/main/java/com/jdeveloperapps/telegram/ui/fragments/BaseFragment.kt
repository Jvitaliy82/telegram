package com.jdeveloperapps.telegram.ui.fragments

import androidx.fragment.app.Fragment
import com.jdeveloperapps.telegram.utilites.APP_ACTIVITY


open class BaseFragment(layout: Int) : Fragment(layout) {

    override fun onStart() {
        super.onStart()
        APP_ACTIVITY.mAppDrawer.disableDrawer()
    }


}