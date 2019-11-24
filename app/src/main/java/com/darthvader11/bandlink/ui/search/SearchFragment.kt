package com.darthvader11.bandlink.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.darthvader11.bandlink.R
import com.darthvader11.bandlink.adaptors.SearchAdapter
import com.darthvader11.bandlink.models.Supplier2
import com.darthvader11.bandlink.ui.post.PostFragment
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment(), View.OnClickListener {

    lateinit var adapter: SearchAdapter
    lateinit var recyclerSearch: RecyclerView
    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        recyclerSearch = root.findViewById(R.id.recyclerSearch)

        val sendSearch : ImageButton = root.findViewById(R.id.sendSearch)
        sendSearch.setOnClickListener(this)
        val logoSearch : ImageView = root.findViewById(R.id.logoSearch)
        logoSearch.setOnClickListener(this)


        return root

    }

    private fun setupRecyclerView(recyclerSearch: RecyclerView){


        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerSearch.layoutManager = layoutManager
        adapter = SearchAdapter(context!!, Supplier2.searchResults)
        recyclerSearch.adapter = adapter



    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.sendSearch -> {
                setupRecyclerView(recyclerSearch)
                inputSearch.setText("")
                inputSearch.clearComposingText()
            }
            R.id.logoSearch -> {
                val manager: FragmentManager? = fragmentManager
                val transaction: FragmentTransaction? = manager?.beginTransaction()
                transaction?.replace(R.id.nav_host_fragment, PostFragment() , PostFragment::class.java.simpleName  )
                transaction?.addToBackStack(null)
                transaction?.commit()

        }

    }


}