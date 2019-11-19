package com.darthvader11.bandlink.comment

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.darthvader11.bandlink.R
import com.darthvader11.bandlink.adaptors.CommentsAdapter
import com.darthvader11.bandlink.adaptors.SuggestionsAdapter
import com.darthvader11.bandlink.models.Supplier
import com.darthvader11.bandlink.models.Supplier2
import kotlinx.android.synthetic.main.activity_comment.*

class CommentPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_comment)

        setupRecyclerView()

    }

    private fun setupRecyclerView(){
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerComments.layoutManager = layoutManager
        val adapter = CommentsAdapter(this, Supplier.comments)
        recyclerComments.adapter = adapter

        val layoutManager2 = LinearLayoutManager(this)
        layoutManager2.orientation = LinearLayoutManager.VERTICAL
        recyclerSuggestions.layoutManager = layoutManager2
        val adapter2 = SuggestionsAdapter(this, Supplier2.suggestions)
        recyclerSuggestions.adapter = adapter2


    }

}