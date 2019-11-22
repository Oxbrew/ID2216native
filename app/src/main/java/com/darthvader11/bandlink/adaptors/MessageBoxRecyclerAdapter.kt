package com.example.messagebox.View

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.darthvader11.bandlink.R
import com.example.messagebox.Model.MessagePreview
import kotlinx.android.synthetic.main.message_box_list_item.view.*

class MessageBoxRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<MessagePreview> = ArrayList()
        private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return MessageBoxPreviewViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.message_box_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder) {
            is MessageBoxPreviewViewHolder -> {
                holder.itemView.setOnClickListener {

                }
                holder.bind(items[position])
            }
        }
    }

    fun submitList(messageList: List<MessagePreview>) {
        items = messageList
    }

    class MessageBoxPreviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val profilePic = itemView.profile_picture
        val senderLastMessage = itemView.sender_last_message
        val senderName = itemView.sender_name

        fun bind(messagePreview: MessagePreview) {

            senderName.text = messagePreview.senderName
            senderLastMessage.text = messagePreview.senderLastMessage

            if (messagePreview.isRead) {
                senderLastMessage.setTypeface(senderLastMessage.typeface, Typeface.BOLD);
            }

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(messagePreview.senderProfilePic)
                .into(profilePic)
        }
    }
}

