package com.darthvader11.bandlink.ui.login

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.Window
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.lifecycle.ViewModelProviders


import com.darthvader11.bandlink.R
import com.darthvader11.bandlink.User
import com.darthvader11.bandlink.UserLocalStore
import com.darthvader11.bandlink.server.GetUserCallback
import com.darthvader11.bandlink.server.ServerRequest
import com.darthvader11.bandlink.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var loginViewModel: LoginViewModel
    lateinit var userLocalStore: UserLocalStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.activity_login)


        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        val loading = findViewById<ProgressBar>(R.id.loading)

        val register : TextView = findViewById(R.id.register_Signup)
        register.setOnClickListener(this)

        userLocalStore = UserLocalStore(this)


        loginViewModel = ViewModelProviders.of(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                email.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            finish()
        })

        email.afterTextChanged {
            loginViewModel.loginDataChanged(
                email.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    email.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            email.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {

                var emailtxt = email.text.toString()
                var passwordtxt = password.text.toString()

                var user: User = User(emailtxt,passwordtxt)

                authenticate(user)
                


                loading.visibility = View.VISIBLE
                loginViewModel.login(email.text.toString(), password.text.toString())
            }
        }


    }

    private fun authenticate(user: User){

        var serverRequest: ServerRequest = ServerRequest(this)
        serverRequest.fetchUserDataInBackground(user, object : GetUserCallback {
            override fun done(returnedUser: User?) {
                if(returnedUser == null){
                    showErrorMessage()
                }
                else{
                    logUserIn(returnedUser)
                }


            }
        })

    }

    private fun logUserIn(user: User){

        userLocalStore.storeUserData(user)
        userLocalStore.setUserLoggedIn(true)
        val intent = Intent(this , com.darthvader11.bandlink.MainActivity::class.java)
        startActivity(intent)



    }

    private fun showErrorMessage(){

        var dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("Incorrect user detail")
        dialogBuilder.setPositiveButton("Ok", null)
        dialogBuilder.show()

    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.register_Signup -> {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)

            }



        }
    }


}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })


}
