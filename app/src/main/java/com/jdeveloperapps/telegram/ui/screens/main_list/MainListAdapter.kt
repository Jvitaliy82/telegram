package com.jdeveloperapps.telegram.ui.screens.main_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jdeveloperapps.telegram.R
import com.jdeveloperapps.telegram.models.CommonModel
import com.jdeveloperapps.telegram.utilites.downloadAndSetImage
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.main_list_item.view.*

class MainListAdapter: RecyclerView.Adapter<MainListAdapter.MainListHolder>() {

    private var listItems = mutableListOf<CommonModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_list_item, parent, false)
        return MainListHolder(view)
    }

    override fun onBindViewHolder(holder: MainListHolder, position: Int) {
        holder.itemName.text = listItems[position].fullname
        holder.itemLastMessage.text = listItems[position].lastMessage
        holder.itemPhoto.downloadAndSetImage(listItems[position].photoUrl)
    }

    override fun getItemCount(): Int = listItems.size

    fun updateListItems(item: CommonModel) {
        listItems.add(item)
        notifyItemInserted(listItems.size)
    }

    class MainListHolder(view: View): RecyclerView.ViewHolder(view) {
        val itemName: TextView = view.main_list_item_name
        val itemLastMessage: TextView = view.main_list_last_message
        val itemPhoto: CircleImageView = view.main_list_item_photo
    }
}