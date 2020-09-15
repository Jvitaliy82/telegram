package com.jdeveloperapps.telegram.ui.screens.main_list

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.jdeveloperapps.telegram.R
import com.jdeveloperapps.telegram.database.*
import com.jdeveloperapps.telegram.models.CommonModel
import com.jdeveloperapps.telegram.utilites.APP_ACTIVITY
import com.jdeveloperapps.telegram.utilites.AppValueEventListener
import com.jdeveloperapps.telegram.utilites.hideKeyboard
import kotlinx.android.synthetic.main.fragment_main_list.*

class ListFragment : Fragment(R.layout.fragment_main_list) {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: MainListAdapter
    private val mRefMAinList = REF_DATABASE_ROOT.child(NODE_MAIN_LIST).child(CURRENT_UID)
    private val mRefUsers = REF_DATABASE_ROOT.child(NODE_USERS)
    private val mRefMessages = REF_DATABASE_ROOT.child(NODE_MESSAGES).child(CURRENT_UID)
    private var mListItems = listOf<CommonModel>()

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = getString(R.string.chat_fragment_title)
        APP_ACTIVITY.mAppDrawer.enableDrawer()
        hideKeyboard()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        mRecyclerView = main_list_recycle_view
        mAdapter = MainListAdapter()

        //1 запрос
        mRefMAinList.addListenerForSingleValueEvent(AppValueEventListener{ data ->
            mListItems = data.children.map { it.getCommonModel() }
            mListItems.forEach { model ->

                //2 запрос
                mRefUsers.child(model.id).addListenerForSingleValueEvent(AppValueEventListener{ data1 ->
                    val newModel = data1.getCommonModel()

                    //3 запрос
                    mRefMessages.child(model.id).limitToLast(1)
                        .addListenerForSingleValueEvent(AppValueEventListener{data2 ->
                            val tempList = data2.children.map { it.getCommonModel() }
                            newModel.lastMessage = tempList[0].text

                            if (newModel.fullname.isEmpty()) {
                                newModel.fullname = newModel.phone
                            }
                            mAdapter.updateListItems(newModel)
                    })
                })
            }
        })

        mRecyclerView.adapter = mAdapter
    }

}