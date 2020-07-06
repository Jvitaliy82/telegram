package com.jdeveloperapps.telegram

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.jdeveloperapps.telegram.activities.RegisterActivity
import com.jdeveloperapps.telegram.databinding.ActivityMainBinding
import com.jdeveloperapps.telegram.ui.fragments.ChatsFragment
import com.jdeveloperapps.telegram.ui.objects.AppDrawer
import com.jdeveloperapps.telegram.utilites.AUTH
import com.jdeveloperapps.telegram.utilites.replaceActivity
import com.jdeveloperapps.telegram.utilites.replaceFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAppDrawer: AppDrawer
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
    }

    private fun initFields() {
        mToolbar = mBinding.mainToolbar
        mAppDrawer = AppDrawer(this, toolbar = mToolbar)
        AUTH = FirebaseAuth.getInstance()
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


}
