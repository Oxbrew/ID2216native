package com.darthvader11.bandlink.ui.newpost


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.darthvader11.bandlink.R

class NewpostFragment : Fragment(), View.OnClickListener {


    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_newpost, container, false)

        val uploadImage : Button = root.findViewById(R.id.uploadImage)
        uploadImage.setOnClickListener(this)

        return root

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.uploadImage -> {
                val gallery = Intent()
                gallery.setType("image/*")
                gallery.setAction(Intent.ACTION_GET_CONTENT)
                startActivityForResult(Intent.createChooser(gallery, "Select picture"), 1)
            }
        }
    }
}