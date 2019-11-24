package com.darthvader11.bandlink.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.darthvader11.bandlink.R
import com.darthvader11.bandlink.adaptors.CommentsAdapter
import com.darthvader11.bandlink.adaptors.FeedAdapter
import com.darthvader11.bandlink.models.Supplier
import com.darthvader11.bandlink.models.feedSupplier
import com.darthvader11.bandlink.ui.newpost.NewpostFragment

class FeedFragment : Fragment(), View.OnClickListener {

    lateinit var adapter: FeedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_feed, container, false)
        val createPost: AppCompatImageButton = root.findViewById(R.id.createPost)
        createPost.setOnClickListener(this)

        val recyclerFeed: RecyclerView = root.findViewById(R.id.recyclerFeed)
        setupRecyclerView(recyclerFeed)

        return root
    }


    private fun setupRecyclerView(recyclerFeed: RecyclerView){


        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerFeed.layoutManager = layoutManager
        adapter = FeedAdapter(context!!, feedSupplier.feedContent)
        recyclerFeed.adapter = adapter



    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.createPost -> {
                Toast.makeText(context, "This button is working", Toast.LENGTH_SHORT).show()
                val manager: FragmentManager? = fragmentManager
                val transaction: FragmentTransaction? = manager?.beginTransaction()
                transaction?.replace(R.id.nav_host_fragment, NewpostFragment(), NewpostFragment::class.java.simpleName)
                transaction?.addToBackStack(null)
                transaction?.commit()
            }
        }


    }
}
