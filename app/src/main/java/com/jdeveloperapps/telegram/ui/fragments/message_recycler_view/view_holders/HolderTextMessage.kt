package com.jdeveloperapps.telegram.ui.fragments.message_recycler_view.view_holders

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.jdeveloperapps.telegram.database.CURRENT_UID
import com.jdeveloperapps.telegram.ui.fragments.message_recycler_view.views.MessageView
import com.jdeveloperapps.telegram.utilites.asTime
import kotlinx.android.synthetic.main.message_item_text.view.*

class HolderTextMessage(view: View): RecyclerView.ViewHolder(view) {
    val blocUserMessage: ConstraintLayout = view.block_user_message
    val chatUserMessage: TextView = view.chat_user_message
    val chatUserMessageTime: TextView = view.chat_user_message_time

    val blocReceiveMessage: ConstraintLayout = view.block_received_message
    val chatReceiveMessage: TextView = view.chat_received_message
    val chatReceiveMessageTime: TextView = view.chat_received_message_time

    fun drawMessageText(holder: HolderTextMessage, view: MessageView) {
        if (view.from == CURRENT_UID) {
            holder.blocUserMessage.visibility = View.VISIBLE
            holder.blocReceiveMessage.visibility = View.GONE
            holder.chatUserMessage.text = view.text
            holder.chatUserMessageTime.text = view.timeStamp.asTime()
        } else {
            holder.blocUserMessage.visibility = View.GONE
            holder.blocReceiveMessage.visibility = View.VISIBLE
            holder.chatReceiveMessage.text = view.text
            holder.chatReceiveMessageTime.text = view.timeStamp.asTime()
        }
    }
}