package com.darthvader11.bandlink.ui

import android.content.Intent
import android.graphics.drawable.ClipDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.darthvader11.bandlink.R
import com.darthvader11.bandlink.adaptors.StartNewChatAdapter
import com.example.messagebox.Model.DummyDataProvider
import com.example.messagebox.Model.MessagePreview
import kotlinx.android.synthetic.main.activity_messega_box.recycler_view
import kotlinx.android.synthetic.main.fragment_messages.*

class StartNewChatActivity : AppCompatActivity() {

    private lateinit var startNewCharAdapter: StartNewChatAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_new_chat)

        initRecyclerView()
        setSearchBar()
        startNewCharAdapter.submitList(DummyDataProvider.dummyMessagePreview())
    }

    private fun initRecyclerView() {

        val itemDecor = DividerItemDecoration(this, ClipDrawable.HORIZONTAL)
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@StartNewChatActivity)
            startNewCharAdapter = StartNewChatAdapter()
            adapter = startNewCharAdapter
            addItemDecoration(itemDecor)
        }

    }

    private fun setSearchBar() {

        val editText = edit_text_of_message_search

        editText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {

                var list = ArrayList<MessagePreview>()

                startNewCharAdapter.items.forEach { element ->
                    p0?.let {
                        if (element.senderName.length >= it.length && it.isNotEmpty() ){
                            if (element.senderName.contains(it.subSequence(0,it.length), ignoreCase = true)) {
                                list.add(element)
                                startNewCharAdapter.submitList(list)
                                startNewCharAdapter.notifyDataSetChanged()
                            } else {
                                startNewCharAdapter.submitList(list)
                                startNewCharAdapter.notifyDataSetChanged()
                            }
                        }
                    }
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                startNewCharAdapter.submitList(DummyDataProvider.dummyMessagePreview())
                startNewCharAdapter.notifyDataSetChanged()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })
    }
}

