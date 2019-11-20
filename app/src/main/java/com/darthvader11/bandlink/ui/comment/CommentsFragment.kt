package com.darthvader11.bandlink.ui.comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.darthvader11.bandlink.R
import com.darthvader11.bandlink.adaptors.CommentsAdapter
import com.darthvader11.bandlink.adaptors.SuggestionsAdapter
import com.darthvader11.bandlink.models.Supplier
import com.darthvader11.bandlink.models.Supplier2
import kotlinx.android.synthetic.main.activity_comment.*

class CommentsFragment : Fragment() {


    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_comments, container, false)
        val recyclerComments: RecyclerView = root.findViewById(R.id.recyclerComments)
        val recyclerSuggestions: RecyclerView = root.findViewById(R.id.recyclerSuggestions)
        setupRecyclerView(recyclerComments, recyclerSuggestions)

        return root

    }

    private fun setupRecyclerView(recyclerComments: RecyclerView, recyclerSuggestions: RecyclerView){


        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerComments.layoutManager = layoutManager
        val adapter = CommentsAdapter(context!!, Supplier.comments)
        recyclerComments.adapter = adapter

        val layoutManager2 = LinearLayoutManager(context)
        layoutManager2.orientation = LinearLayoutManager.VERTICAL
        recyclerSuggestions.layoutManager = layoutManager2
        val adapter2 = SuggestionsAdapter(context!!, Supplier2.suggestions)
        recyclerSuggestions.adapter = adapter2


    }


}