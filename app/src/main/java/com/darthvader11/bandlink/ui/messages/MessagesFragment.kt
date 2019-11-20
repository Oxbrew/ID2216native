package com.darthvader11.bandlink.ui.messages
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.darthvader11.bandlink.R
import com.darthvader11.bandlink.comment.CommentPage
import kotlinx.android.synthetic.main.activity_post.*

class MessagesFragment : Fragment() {

    private lateinit var messagesViewModel: MessagesViewModel

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        messagesViewModel =
                ViewModelProviders.of(this).get(MessagesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_messages, container, false)
        val textView: TextView = root.findViewById(R.id.text_messages)
        messagesViewModel.text.observe(this, Observer {
            textView.text = it
        })



        return root
    }
}