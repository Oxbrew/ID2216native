package com.darthvader11.bandlink.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.darthvader11.bandlink.R
import com.darthvader11.bandlink.ui.comment.PostlistFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import android.content.Intent
import android.net.Uri

class ProfileFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        val submitButton: Button = root.findViewById(R.id.submitButton)
        submitButton.setOnClickListener(this)
        val backButton : ImageView = root.findViewById(R.id.backButton)
        backButton.setOnClickListener(this)
        val btnPosts: Button = root.findViewById(R.id.btnPosts)
        btnPosts.setOnClickListener(this)
        val btnInstagram : ImageView = root.findViewById(R.id.btnInstagram)
        btnInstagram .setOnClickListener(this)
        val btnYoutube : ImageView = root.findViewById(R.id.btnYoutube)
        btnYoutube.setOnClickListener(this)
        val btnSpotify : ImageView = root.findViewById(R.id.btnSpotify)
        btnSpotify.setOnClickListener(this)
        return root
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.backButton -> {
                fragmentManager?.popBackStack()
            }
            R.id.submitButton -> {

                //   val totalStarts = "total Stars :" + ratingBar.numStars
                val rating = "Rating :" +  ratingBar.rating
                Toast.makeText(context, rating, Toast.LENGTH_SHORT).show()

            }

                R.id.btnInstagram -> {
                val webIntent: Intent = Uri.parse("http://www.Instagram.com").let {
                        webpage -> Intent(Intent.ACTION_VIEW, webpage)
                }
                startActivity(webIntent)
            }
            R.id.btnYoutube -> {
                val webIntent: Intent = Uri.parse("http://www.youtube.com").let {
                        webpage -> Intent(Intent.ACTION_VIEW, webpage)
                }
                startActivity(webIntent)
            }
            R.id.btnSpotify -> {
                val webIntent: Intent = Uri.parse("http://www.spotify.com").let {
                        webpage -> Intent(Intent.ACTION_VIEW, webpage)
                }
                startActivity(webIntent)
            }

            R.id.btnPosts -> {
                Toast.makeText(context, "Posts", Toast.LENGTH_SHORT).show()
                val manager: FragmentManager? = fragmentManager
                val transaction: FragmentTransaction? = manager?.beginTransaction()
                transaction?.replace(R.id.nav_host_fragment, PostlistFragment() , PostlistFragment::class.java.simpleName  )
                transaction?.addToBackStack(null)
                transaction?.commit()
            }
        }
    }
}