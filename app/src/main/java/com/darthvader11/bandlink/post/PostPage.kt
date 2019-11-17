package com.darthvader11.bandlink.post

import android.content.res.Resources
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.darthvader11.bandlink.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class PostPage : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener{



    override fun onNavigationItemSelected(p0: MenuItem): Boolean {

        when(p0.itemId){
            R.id.btn_search -> {
                Toast.makeText(this, "Search was pressed", Toast.LENGTH_SHORT).show()
                val anim: Animation = AnimationUtils.loadAnimation(this, R.anim.bounce)
            }
            R.id.btn_home ->
                Toast.makeText(this, "Home was pressed", Toast.LENGTH_SHORT).show()
            R.id.btn_messages ->
                Toast.makeText(this, "Messages was pressed", Toast.LENGTH_SHORT).show()
            R.id.btn_profile ->
                Toast.makeText(this, "Profile was pressed", Toast.LENGTH_SHORT).show()
            else ->
                Toast.makeText(this, "You should not see this, it is a bug!", Toast.LENGTH_LONG).show()
        }

        return true;
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)

    }
}