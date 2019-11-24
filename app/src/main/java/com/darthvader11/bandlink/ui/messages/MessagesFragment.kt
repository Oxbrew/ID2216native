package com.darthvader11.bandlink.ui.messages

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.graphics.fonts.Font
import android.graphics.fonts.FontStyle
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.TextClock
import android.widget.TextView
import androidx.constraintlayout.solver.widgets.Rectangle
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.darthvader11.bandlink.R
import com.darthvader11.bandlink.ui.ChatActivity
import com.example.messagebox.Model.DummyDataProvider
import com.example.messagebox.Model.MessagePreview
import com.example.messagebox.View.MessageBoxRecyclerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_messega_box.*
import kotlinx.android.synthetic.main.activity_messega_box.recycler_view
import kotlinx.android.synthetic.main.fragment_messages.*
import kotlinx.android.synthetic.main.fragment_messages.view.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.message_box_list_item.*

class MessagesFragment : Fragment() {

    private lateinit var messagePreviewAdapter: MessageBoxRecyclerAdapter

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_messages, container, false)
        return root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRecyclerView()
        initFabButton()
        setSearchBar()
        messagePreviewAdapter.submitList(DummyDataProvider.dummyMessagePreview())
    }

    private fun initRecyclerView() {

        val itemDecor = DividerItemDecoration(activity, HORIZONTAL)
        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            messagePreviewAdapter = MessageBoxRecyclerAdapter()
            adapter = messagePreviewAdapter
            addItemDecoration(itemDecor)
        }

    }

    private fun initFabButton() {

        val floatingActionButton = fabMessages
        floatingActionButton.setOnClickListener {
            val intent = Intent(activity, ChatActivity::class.java)
            startActivity(intent)

        }
    }

    private fun setSearchBar() {

        val editText = editTextofMessageSearch
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

                val invisibleSearchView = invisibleSearchView
                invisibleSearchView.isVisible = true

                messagePreviewAdapter.items.forEach { element ->
                    p0?.let {
                        if (element.senderName.length >= p0.length && p0.isNotEmpty() ){
                            if (element.senderName.contains(p0.subSequence(0,p0.length), ignoreCase = true)) {

                                val textView = TextView(activity)
                                textView.textSize = 20.toFloat()
                                textView.setBackgroundResource(R.drawable.text_view_border)
                                textView.setPadding(20,30,20,30)

                                textView.text = element.senderName

//                                textView.setOnClickListener {
//                                    messagePreviewAdapter.items.forEachIndexed { index, messagePreview ->
//                                       if (messagePreview.senderName == textView.text) {
//                                       }
//                                    }
//                                }
                                invisibleSearchView.addView(textView)
                            }
                        }
                    }
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val invisibleView = invisibleSearchView
                invisibleView.removeAllViewsInLayout()
                invisibleView.isVisible = false
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })
    }
}

