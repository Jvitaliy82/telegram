package com.jdeveloperapps.telegram.ui.fragments.message_recycler_view.view_holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.jdeveloperapps.telegram.database.CURRENT_UID
import com.jdeveloperapps.telegram.ui.fragments.message_recycler_view.views.MessageView
import com.jdeveloperapps.telegram.utilites.asTime
import com.jdeveloperapps.telegram.utilites.downloadAndSetImage
import kotlinx.android.synthetic.main.message_item_image.view.*

class HolderImageMessage (view: View): RecyclerView.ViewHolder(view) {
    val blockReceivedImageMessage: ConstraintLayout = view.block_received_image
    val blockUserImageMessage: ConstraintLayout = view.block_user_image
    val chatUserImage: ImageView = view.chat_user_image
    val chatReceivedImage: ImageView = view.chat_received_image
    val chatUserImageMessageTime: TextView = view.chat_user_image_message_time
    val chatReceivedImageMessageTime: TextView = view.chat_received_image_message_time

    fun drawMessageImage(holder: HolderImageMessage, view: MessageView) {
        if (view.from == CURRENT_UID) {
            holder.blockUserImageMessage.visibility = View.VISIBLE
            holder.blockReceivedImageMessage.visibility = View.GONE
            holder.chatUserImage.downloadAndSetImage(view.fileUrl)
            holder.chatUserImageMessageTime.text = view.timeStamp.asTime()
        } else {
            holder.blockUserImageMessage.visibility = View.GONE
            holder.blockReceivedImageMessage.visibility = View.VISIBLE
            holder.chatReceivedImage.downloadAndSetImage(view.fileUrl)
            holder.chatReceivedImageMessageTime.text = view.timeStamp.asTime()
        }
    }
}