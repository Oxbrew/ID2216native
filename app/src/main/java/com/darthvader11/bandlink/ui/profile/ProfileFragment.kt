package com.darthvader11.bandlink.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.darthvader11.bandlink.R
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        val submitButton: Button = root.findViewById(R.id.submitButton)
        submitButton.setOnClickListener(this)
        return root
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.submitButton -> {

                //   val totalStarts = "total Stars :" + ratingBar.numStars
                val rating = "Rating :" +  ratingBar.rating
                Toast.makeText(context, rating, Toast.LENGTH_SHORT).show()

            }
        }
    }
}