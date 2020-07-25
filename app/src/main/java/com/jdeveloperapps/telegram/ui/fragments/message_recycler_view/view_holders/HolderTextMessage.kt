package com.jdeveloperapps.telegram.ui.fragments.message_recycler_view.view_holders

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.message_item_text.view.*

class HolderTextMessage(view: View): RecyclerView.ViewHolder(view) {
    val blocUserMessage: ConstraintLayout = view.block_user_message
    val chatUserMessage: TextView = view.chat_user_message
    val chatUserMessageTime: TextView = view.chat_user_message_time

    val blocReceiveMessage: ConstraintLayout = view.block_received_message
    val chatReceiveMessage: TextView = view.chat_received_message
    val chatReceiveMessageTime: TextView = view.chat_received_message_time
}