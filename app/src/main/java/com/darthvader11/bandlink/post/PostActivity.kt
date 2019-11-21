package com.darthvader11.bandlink.post

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_post.*

class PostActivity : AppCompatActivity(){




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)



        btnShare!!.setOnClickListener {
            val intent = Intent()
            val message: String = "calincapitanu.com/Post.html"
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, message)
            intent.type = "text/plain"

            startActivity(Intent.createChooser(intent, "Share to: "))
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }


}