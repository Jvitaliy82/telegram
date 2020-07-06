package com.jdeveloperapps.telegram.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.jdeveloperapps.telegram.MainActivity
import com.jdeveloperapps.telegram.R
import com.jdeveloperapps.telegram.utilites.*
import kotlinx.android.synthetic.main.fragment_chage_user_name.*
import java.util.*

class ChangeUserNameFragment : BaseFragment(R.layout.fragment_chage_user_name) {

    lateinit var mNewUserName: String

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        settings_input_user_name.setText(USER.username)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (activity as MainActivity).menuInflater.inflate(R.menu.settings_confirm_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.settings_confirm_change -> change()
        }
        return true
    }

    private fun change() {
        mNewUserName = settings_input_user_name.text.toString().toLowerCase(Locale.getDefault())
        if (mNewUserName.isEmpty()) {
            showToast("user name is empty")
        } else {
            REF_DATABASE_ROOT.child(NODE_USERNAMES)
                .addListenerForSingleValueEvent(AppValueEventListener{
                    if (it.hasChild(mNewUserName)) {
                        showToast("This user is exist")
                    } else {
                        changeUserName()
                    }
                })

        }
    }

    private fun changeUserName() {
        REF_DATABASE_ROOT.child(NODE_USERNAMES).child(mNewUserName).setValue(UID)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    updateCurentUserName()
                }
            }
    }

    private fun updateCurentUserName() {
        REF_DATABASE_ROOT.child(NODE_USERS).child(UID).child(CHILD_USERNAME).setValue(mNewUserName)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    deleteOldUserName()
                } else {
                    showToast(it.exception?.message.toString())
                }
            }

    }

    private fun deleteOldUserName() {
        REF_DATABASE_ROOT.child(NODE_USERNAMES).child(USER.username).removeValue()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    showToast(getString(R.string.toast_data_update))
                    USER.username = mNewUserName
                    fragmentManager?.popBackStack()
                } else {
                    showToast(it.exception?.message.toString())
                }
            }
    }
}