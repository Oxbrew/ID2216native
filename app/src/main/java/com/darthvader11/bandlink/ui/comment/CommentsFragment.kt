package com.darthvader11.bandlink.ui.comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.darthvader11.bandlink.R
import com.darthvader11.bandlink.adaptors.CommentsAdapter
import com.darthvader11.bandlink.models.Comment
import com.darthvader11.bandlink.models.Supplier
import kotlinx.android.synthetic.main.fragment_comments.*

class CommentsFragment : Fragment(), View.OnClickListener {

    lateinit var adapter: CommentsAdapter

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_comments, container, false)
        val recyclerComments: RecyclerView = root.findViewById(R.id.recyclerComments)
        setupRecyclerView(recyclerComments)

        val sendComment : ImageButton = root.findViewById(R.id.sendComment)
        sendComment.setOnClickListener(this)

        return root

    }

    private fun setupRecyclerView(recyclerComments: RecyclerView){


        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerComments.layoutManager = layoutManager
        adapter = CommentsAdapter(context!!, Supplier.comments)
        recyclerComments.adapter = adapter



    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.sendComment -> {
                val message = inputComment.text.toString()
                Supplier.comments.add(Supplier.comments.lastIndex + 1, Comment("TestName", message, R.drawable.profilezero))
                adapter.notifyDataSetChanged()
                inputComment.setText("")
                inputComment.clearComposingText()
            }



        }

    }


}