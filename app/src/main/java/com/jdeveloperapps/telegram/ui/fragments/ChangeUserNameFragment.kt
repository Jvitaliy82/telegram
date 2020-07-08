package com.jdeveloperapps.telegram.ui.fragments

import com.jdeveloperapps.telegram.R
import com.jdeveloperapps.telegram.database.*
import com.jdeveloperapps.telegram.utilites.AppValueEventListener
import com.jdeveloperapps.telegram.utilites.showToast
import kotlinx.android.synthetic.main.fragment_chage_user_name.*
import java.util.*

class ChangeUserNameFragment : BaseChangeFragment(R.layout.fragment_chage_user_name) {

    lateinit var mNewUserName: String

    override fun onResume() {
        super.onResume()
        settings_input_user_name.setText(USER.username)
    }


    override fun change() {
        mNewUserName = settings_input_user_name.text.toString().toLowerCase(Locale.getDefault())
        if (mNewUserName.isEmpty()) {
            showToast("user name is empty")
        } else {
            REF_DATABASE_ROOT.child(
                NODE_USERNAMES
            )
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
        REF_DATABASE_ROOT.child(
            NODE_USERNAMES
        ).child(mNewUserName).setValue(CURRENT_UID)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    updateCurrentUserName(mNewUserName)
                }
            }
    }


}