package com.jdeveloperapps.telegram.ui.message_recycler_view.view_holders

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.jdeveloperapps.telegram.database.CURRENT_UID
import com.jdeveloperapps.telegram.ui.message_recycler_view.views.MessageView
import com.jdeveloperapps.telegram.utilites.asTime
import kotlinx.android.synthetic.main.message_item_text.view.*

class HolderTextMessage(view: View): RecyclerView.ViewHolder(view), MessageHolder {
    val blocUserMessage: ConstraintLayout = view.block_user_message
    val chatUserMessage: TextView = view.chat_user_message
    val chatUserMessageTime: TextView = view.chat_user_message_time

    val blocReceiveMessage: ConstraintLayout = view.block_received_message
    val chatReceiveMessage: TextView = view.chat_received_message
    val chatReceiveMessageTime: TextView = view.chat_received_message_time


    override fun drawMessage(view: MessageView) {
        if (view.from == CURRENT_UID) {
            blocUserMessage.visibility = View.VISIBLE
            blocReceiveMessage.visibility = View.GONE
            chatUserMessage.text = view.text
            chatUserMessageTime.text = view.timeStamp.asTime()
        } else {
            blocUserMessage.visibility = View.GONE
            blocReceiveMessage.visibility = View.VISIBLE
            chatReceiveMessage.text = view.text
            chatReceiveMessageTime.text = view.timeStamp.asTime()
        }
    }

    override fun onAttach(view: MessageView) {

    }

    override fun onDettach() {

    }
}