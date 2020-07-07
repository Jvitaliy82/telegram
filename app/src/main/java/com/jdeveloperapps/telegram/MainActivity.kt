package com.jdeveloperapps.telegram

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.jdeveloperapps.telegram.activities.RegisterActivity
import com.jdeveloperapps.telegram.databinding.ActivityMainBinding
import com.jdeveloperapps.telegram.models.User
import com.jdeveloperapps.telegram.ui.fragments.ChatsFragment
import com.jdeveloperapps.telegram.ui.objects.AppDrawer
import com.jdeveloperapps.telegram.utilites.*
import com.theartofdev.edmodo.cropper.CropImage

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    lateinit var mAppDrawer: AppDrawer
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        APP_ACTIVITY = this
        initFields()
        initFunc()
    }

    private fun initFields() {
        mToolbar = mBinding.mainToolbar
        mAppDrawer = AppDrawer(this, toolbar = mToolbar)
        initFirebase()
        initUser()
    }

    private fun initFunc() {
        if (AUTH.currentUser != null) {
            setSupportActionBar(mToolbar)
            mAppDrawer.create()
            replaceFragment(ChatsFragment(), false)
        } else {
            replaceActivity(RegisterActivity())
        }
    }

    private fun initUser() {
        REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID)
            .addListenerForSingleValueEvent(AppValueEventListener{
                USER = it.getValue(User::class.java) ?: User()
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE
            && resultCode == Activity.RESULT_OK && data != null) {
            val uri = CropImage.getActivityResult(data).uri
            val path = REF_STORAGE_ROOT.child(FOLDER_PROFILE_IMAGE).child(CURRENT_UID)
            path.putFile(uri).addOnCompleteListener { tack1 ->
                if (tack1.isSuccessful) {
                    path.downloadUrl.addOnCompleteListener {tack2 ->
                        if (tack2.isSuccessful) {
                            val photoUrl = tack2.result.toString()
                            REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(
                                CHILD_PHOTOURL).setValue(photoUrl).addOnCompleteListener {
                                if (it.isSuccessful) {
                                    showToast(getString(R.string.toast_data_update))
                                    USER.photoUrl = photoUrl
                                }
                            }
                        }
                    }
                } else {
                    showToast(tack1.exception?.message.toString())
                }
            }
        }
    }

    fun hideKeyboard() {
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
    }

}
