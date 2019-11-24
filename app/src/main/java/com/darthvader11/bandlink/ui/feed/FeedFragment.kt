package com.darthvader11.bandlink.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.darthvader11.bandlink.R
import com.darthvader11.bandlink.ui.newpost.NewpostFragment

class FeedFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_feed, container, false)
        val createPost: Button = root.findViewById(R.id.createPost)
        createPost.setOnClickListener(this)
        return root
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
