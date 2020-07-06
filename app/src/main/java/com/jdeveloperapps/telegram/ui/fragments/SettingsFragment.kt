package com.jdeveloperapps.telegram.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.jdeveloperapps.telegram.MainActivity
import com.jdeveloperapps.telegram.R
import com.jdeveloperapps.telegram.activities.RegisterActivity
import com.jdeveloperapps.telegram.utilites.AUTH
import com.jdeveloperapps.telegram.utilites.USER
import com.jdeveloperapps.telegram.utilites.replaceActivity
import com.jdeveloperapps.telegram.utilites.replaceFragment
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        initFields()
    }

    private fun initFields() {
        settings_full_name.text = USER.fullname
        settings_status.text = USER.status
        settings_phone_number.text = USER.phone
        settings_username.text = USER.username
        settings_bio.text = USER.bio
        settings_btn_change_username.setOnClickListener {
            replaceFragment(ChangeUserNameFragment())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.settings_menu_exit -> {
                AUTH.signOut()
                (activity as MainActivity).replaceActivity(RegisterActivity())
            }
            R.id.settings_menu_change_name -> {
                replaceFragment(ChangeNameFragment())
            }
        }
        return true
    }
}