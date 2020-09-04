package com.jdeveloperapps.telegram.ui.message_recycler_view.view_holders

import android.os.Environment
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.jdeveloperapps.telegram.database.CURRENT_UID
import com.jdeveloperapps.telegram.database.getFileFromStorage
import com.jdeveloperapps.telegram.ui.message_recycler_view.views.MessageView
import com.jdeveloperapps.telegram.utilites.WRITE_FILES
import com.jdeveloperapps.telegram.utilites.asTime
import com.jdeveloperapps.telegram.utilites.checkPermissions
import com.jdeveloperapps.telegram.utilites.showToast
import kotlinx.android.synthetic.main.message_item_file.view.*
import java.io.File

class HolderFileMessage(view: View) : RecyclerView.ViewHolder(view), MessageHolder {

    private val blockReceivedFileMessage: ConstraintLayout = view.block_received_file_message
    private val blockUserFileMessage: ConstraintLayout = view.block_user_file_message
    private val chatUserFileMessageTime: TextView = view.chat_user_file_message_time
    private val chatReceivedFileMessageTime: TextView = view.chat_received_file_message_time

    private val chatUserFileNme: TextView = view.chat_user_filename
    private val chatUserBtnDownload: ImageView = view.chat_user_btn_download
    private val chatUserProgressBar: ProgressBar = view.chat_user_progressBar

    private val chatReceivedFileNme: TextView = view.chat_received_filename
    private val chatReceivedBtnDownload: ImageView = view.chat_received_btn_download
    private val chatReceivedProgressBar: ProgressBar = view.chat_received_progressBar

    override fun drawMessage(view: MessageView) {
        if (view.from == CURRENT_UID) {
            blockUserFileMessage.visibility = View.VISIBLE
            blockReceivedFileMessage.visibility = View.GONE
            chatUserFileMessageTime.text = view.timeStamp.asTime()
            chatUserFileNme.text = view.text
        } else {
            blockUserFileMessage.visibility = View.GONE
            blockReceivedFileMessage.visibility = View.VISIBLE
            chatReceivedFileMessageTime.text = view.timeStamp.asTime()
            chatReceivedFileNme.text = view.text
        }
    }

    override fun onAttach(view: MessageView) {
        if (view.from == CURRENT_UID) chatUserBtnDownload.setOnClickListener { clickToBtnFile(view) }
        else chatReceivedBtnDownload.setOnClickListener { clickToBtnFile(view) }
    }

    private fun clickToBtnFile(view: MessageView) {
        if (view.from == CURRENT_UID) {
            chatUserBtnDownload.visibility = View.INVISIBLE
            chatUserProgressBar.visibility = View.VISIBLE
        } else {
            chatReceivedBtnDownload.visibility = View.INVISIBLE
            chatReceivedProgressBar.visibility = View.VISIBLE
        }

        val file = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            view.text
        )

        try {
            if (checkPermissions(WRITE_FILES)) {
                file.createNewFile()
                getFileFromStorage(file, view.fileUrl) {
                    if (view.from == CURRENT_UID) {
                        chatUserBtnDownload.visibility = View.VISIBLE
                        chatUserProgressBar.visibility = View.INVISIBLE
                    } else {
                        chatReceivedBtnDownload.visibility = View.VISIBLE
                        chatReceivedProgressBar.visibility = View.INVISIBLE
                    }
                }
            }
        } catch (e: Exception) {
            showToast(e.message.toString())
        }
    }

    override fun onDettach() {
        chatUserBtnDownload.setOnClickListener { null }
        chatReceivedBtnDownload.setOnClickListener { null }
    }


}