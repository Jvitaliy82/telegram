package com.jdeveloperapps.telegram.ui.fragments.single_chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.jdeveloperapps.telegram.R
import com.jdeveloperapps.telegram.database.CURRENT_UID
import com.jdeveloperapps.telegram.models.CommonModel
import com.jdeveloperapps.telegram.utilites.TYPE_MESSAGE_IMAGE
import com.jdeveloperapps.telegram.utilites.TYPE_MESSAGE_TEXT
import com.jdeveloperapps.telegram.utilites.asTime
import com.jdeveloperapps.telegram.utilites.downloadAndSetImage
import kotlinx.android.synthetic.main.message_item.view.*

class SingleChatAdapter: RecyclerView.Adapter<SingleChatAdapter.SingleChatHolder>() {

    private var mListMessagesCache = mutableListOf<CommonModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleChatHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
        return SingleChatHolder(view)
    }

    override fun getItemCount(): Int = mListMessagesCache.size

    override fun onBindViewHolder(holder: SingleChatHolder, position: Int) {
        when (mListMessagesCache[position].type) {
            TYPE_MESSAGE_TEXT -> drawMessageText(holder, position)
            TYPE_MESSAGE_IMAGE -> drawMessageImage(holder, position)
        }

    }

    private fun drawMessageText(holder: SingleChatHolder, position: Int) {
        holder.blockUserImageMessage.visibility = View.GONE
        holder.blockReceivedImageMessage.visibility = View.GONE
        if (mListMessagesCache[position].from == CURRENT_UID) {
            holder.blocUserMessage.visibility = View.VISIBLE
            holder.blocReceiveMessage.visibility = View.GONE
            holder.chatUserMessage.text = mListMessagesCache[position].text
            holder.chatUserMessageTime.text = mListMessagesCache[position]
                .timeStamp.toString().asTime()
        } else {
            holder.blocUserMessage.visibility = View.GONE
            holder.blocReceiveMessage.visibility = View.VISIBLE
            holder.chatReceiveMessage.text = mListMessagesCache[position].text
            holder.chatReceiveMessageTime.text = mListMessagesCache[position]
                .timeStamp.toString().asTime()
        }
    }

    private fun drawMessageImage(holder: SingleChatHolder, position: Int) {
        holder.blocUserMessage.visibility = View.GONE
        holder.blocReceiveMessage.visibility = View.GONE
        if (mListMessagesCache[position].from == CURRENT_UID) {
            holder.blockUserImageMessage.visibility = View.VISIBLE
            holder.blockReceivedImageMessage.visibility = View.GONE
            holder.chatUserImage.downloadAndSetImage(mListMessagesCache[position].fileUrl)
            holder.chatUserImageMessageTime.text = mListMessagesCache[position]
                .timeStamp.toString().asTime()
        } else {
            holder.blockUserImageMessage.visibility = View.GONE
            holder.blockReceivedImageMessage.visibility = View.VISIBLE
            holder.chatReceivedImage.downloadAndSetImage(mListMessagesCache[position].fileUrl)
            holder.chatReceivedImageMessageTime.text = mListMessagesCache[position]
                .timeStamp.toString().asTime()
        }
    }

    fun addItemToBottom(item: CommonModel, onSuccess: () -> Unit) {
        if (!mListMessagesCache.contains(item)) {
            mListMessagesCache.add(item)
            notifyItemInserted(mListMessagesCache.size)
        }
        onSuccess()
    }

    fun addItemToTop(item: CommonModel, onSuccess: () -> Unit) {
        if (!mListMessagesCache.contains(item)) {
            mListMessagesCache.add(item)
            mListMessagesCache.sortBy { it.timeStamp.toString() }
            notifyItemInserted(0)
        }
        onSuccess()
    }

    class SingleChatHolder(view: View): RecyclerView.ViewHolder(view) {
        //text
        val blocUserMessage: ConstraintLayout = view.block_user_message
        val chatUserMessage: TextView = view.chat_user_message
        val chatUserMessageTime: TextView = view.chat_user_message_time

        val blocReceiveMessage: ConstraintLayout = view.block_received_message
        val chatReceiveMessage: TextView = view.chat_received_message
        val chatReceiveMessageTime: TextView = view.chat_received_message_time

        //Image
        val blockReceivedImageMessage: ConstraintLayout = view.block_received_image
        val blockUserImageMessage: ConstraintLayout = view.block_user_image
        val chatUserImage: ImageView = view.chat_user_image
        val chatReceivedImage: ImageView = view.chat_received_image
        val chatUserImageMessageTime: TextView = view.chat_user_image_message_time
        val chatReceivedImageMessageTime: TextView = view.chat_received_image_message_time
    }
}


