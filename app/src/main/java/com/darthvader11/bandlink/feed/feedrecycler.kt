package com.darthvader11.bandlink.feed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.darthvader11.bandlink.R

class feedrecycler : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedrecycler)
        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerComments.layoutManager = layoutManager
        val adapter = CommentsAdapter(this, Supplier.comments)
        recyclerComments.adapter = adapter

    }
}
