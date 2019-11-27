package com.darthvader11.bandlink


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.darthvader11.bandlink.models.Comment
import com.darthvader11.bandlink.models.Supplier
import com.darthvader11.bandlink.ui.login.LoginActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var userLocalStore: UserLocalStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navView.setupWithNavController(navController)
        this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        userLocalStore = UserLocalStore(this)

        if(findViewById<Button>(R.id.logoutBtn) != null) {
            var logoutBtn: Button = findViewById(R.id.logoutBtn)
            logoutBtn.setOnClickListener(this)
        }


    }

    override fun onStart() {
        super.onStart()
        if(authenticate() == true){
            displayUserDetails()
        }

    }

    fun displayUserDetails() {
        var user: User = userLocalStore.getLoggedInUser()
        Supplier.comments.add(Comment(user.username, user.username, R.drawable.profilezero))
        Supplier.comments.add(Comment(user.username, user.email, R.drawable.profilezero))
        Supplier.comments.add(Comment(user.username, user.password, R.drawable.profilezero))

    }


    private fun authenticate() : Boolean {
        return userLocalStore.getUserLoggedIn()

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.logoutBtn -> {
                userLocalStore.clearUserData()
                userLocalStore.setUserLoggedIn(false)


                var intent: Intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }

        }


    }



}
