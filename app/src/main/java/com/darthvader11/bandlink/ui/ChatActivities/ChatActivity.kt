package com.darthvader11.bandlink.ui.ChatActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.darthvader11.bandlink.MessagingNetwork.*
import com.darthvader11.bandlink.R
import com.darthvader11.bandlink.adaptors.MessageListAdapter
import com.darthvader11.bandlink.server.ServerRequest
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_message_list.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class ChatActivity : AppCompatActivity() {

    private lateinit var messageListAdapter: MessageListAdapter
    private val api = MessagingAPI()
    private val messagesHandler = Handler(Looper.getMainLooper())
    private var sessionID: Int? = null
    private var targetUserID: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_list)

        initRecyclerView()

//        var serverRequest1 = ServerRequest(this, R.layout.activity_message_list)
//        val a = serverRequest1.getUserId(this)
//
//        println(a)
        this.sessionID = intent.getIntExtra("sessionID",0)
        this.targetUserID = intent.getIntExtra("userID", 0)

        if (this.sessionID != 0) {
            getMessages(this.sessionID!!)
        } else {
            val targetUserId = this.targetUserID
            print("###### TargetUserId = $targetUserId")
            if (targetUserId != 0) {
                val messageSessionRequest = MessageSessionRequest(20, targetUserId!!)
                api.postSession(messageSessionRequest, object: Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        println("#### ERROR")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val jsonString = response.body!!.string()
                        val postSessionResponse: PostSessionResponse = Gson().fromJson(jsonString, PostSessionResponse::class.java)
                        val sessionId = postSessionResponse.session_id
                        sessionID = sessionId
                        getMessages(sessionId)
                    }
                })
            } else {
                println("Something went wrong")
            }
        }

        val editText = edittext_chatbox
        val sendButton: Button = button_chatbox_send
        sendButton.setOnClickListener {

            if (sessionID!! != 0) {
                val message = MessageRequest(editText.text.toString(), 20, sessionID!!)

                api.postMessage(message, object : Callback {
                    override fun onResponse(call: Call, response: Response) {
                        print(response.isSuccessful)
                    }

                    override fun onFailure(call: Call, e: IOException) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }
                })

                editText.text.clear()
            }
        }

        messagesHandler.post(object : Runnable {
            override fun run() {
                if(sessionID!! != 0) {
                    api.getMessages(sessionID!!, object : Callback {
                        override fun onResponse(call: Call, response: Response) {
                            populateMessages(response)
                        }

                        override fun onFailure(call: Call, e: IOException) {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        }
                    })
                }
                messagesHandler.postDelayed(this, 1000)
            }
        })
    }

    private fun getMessages(sessionId: Int) {
        api.getMessages(sessionId, object : Callback {
            override fun onResponse(call: Call, response: Response) {
                populateMessages(response)
            }

            override fun onFailure(call: Call, e: IOException) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    private fun initRecyclerView() {

        reyclerview_message_list.apply {
            layoutManager = LinearLayoutManager(this@ChatActivity)
            messageListAdapter = MessageListAdapter()
            adapter = messageListAdapter
        }
    }

    private fun populateMessages(response: Response) {
            if (response.body != null) {
                val jsonString = response.body!!.string()
                val messageResponseList: List<MessageResponseForSession> = Gson().fromJson(jsonString, Array<MessageResponseForSession>::class.java).toList()
                this.runOnUiThread {
                messageListAdapter.submitList(messageResponseList)
                messageListAdapter.notifyDataSetChanged()
                reyclerview_message_list.scrollToPosition(messageListAdapter.items.size - 1)
            }
        }
    }
}
