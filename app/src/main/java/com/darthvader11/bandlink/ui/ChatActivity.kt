package com.darthvader11.bandlink.ui

import android.graphics.drawable.ClipDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.darthvader11.bandlink.R
import com.darthvader11.bandlink.adaptors.MessageListAdapter
import com.darthvader11.bandlink.data.model.DummyChatProvider
import com.example.messagebox.View.MessageBoxRecyclerAdapter
import kotlinx.android.synthetic.main.activity_message_list.*
import kotlinx.android.synthetic.main.activity_messega_box.*

class ChatActivity : AppCompatActivity() {

    private lateinit var messageListAdapter: MessageListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_list)

        initRecyclerView()
        messageListAdapter.submitList(DummyChatProvider.dummyMessageList())
        reyclerview_message_list.scrollToPosition(messageListAdapter.items.size - 1)
    }


    private fun initRecyclerView() {

        reyclerview_message_list.apply {
            layoutManager = LinearLayoutManager(this@ChatActivity)
            messageListAdapter = MessageListAdapter()
            adapter = messageListAdapter
        }

    }
}
