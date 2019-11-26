package com.darthvader11.bandlink

import android.content.Context
import android.content.SharedPreferences

class UserLocalStore {

    val SP_NAME: String = "userDetails"
    var userLocalDatabase: SharedPreferences

    constructor(context: Context){
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0)
    }

    fun storeUserData(user: User){
        var spEditor: SharedPreferences.Editor = userLocalDatabase.edit()
        spEditor.putString("name", user.username)
        spEditor.putString("email", user.email)
        spEditor.putString("password", user.password)
        spEditor.apply()

    }

    fun getLoggedInUser() : User {
        var username: String? = userLocalDatabase.getString("name", "")
        var password : String? = userLocalDatabase.getString("password", "")
        var mail : String? = userLocalDatabase.getString("email", "")
        return User(username.toString(),mail.toString(),password.toString())
    }

    fun setUserLoggedIn (loggedIn : Boolean) {
        var spEditor: SharedPreferences.Editor = userLocalDatabase.edit()
        spEditor.putBoolean("loggedIn", loggedIn)
        spEditor.apply()
    }

    fun clearUserData(){
        var spEditor: SharedPreferences.Editor = userLocalDatabase.edit()
        spEditor.clear()
        spEditor.apply()
    }

    fun getUserLoggedIn() : Boolean{
        if(userLocalDatabase.getBoolean("loggedIn", false)){
            return true
        }
        return false
    }

}