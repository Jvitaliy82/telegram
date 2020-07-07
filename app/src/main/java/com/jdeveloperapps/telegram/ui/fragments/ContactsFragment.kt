package com.jdeveloperapps.telegram.ui.fragments

import com.jdeveloperapps.telegram.R
import com.jdeveloperapps.telegram.utilites.APP_ACTIVITY


class ContactsFragment : BaseFragment(R.layout.fragment_contacts) {

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = getString(R.string.activity_title_contacts)
    }

}