package com.darthvader11.bandlink.server

import android.content.Context
import android.net.Uri
import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.darthvader11.bandlink.R
import com.darthvader11.bandlink.User
import com.darthvader11.bandlink.ui.register.RegisterActivity
import org.apache.http.HttpEntity
import org.json.JSONObject
import java.io.*
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class ServerRequest {

    var progressDialog: ProgressBar
    val CONNECTION_TIMEOUT: Int = 1000 * 15
    val SERVER_ADDRESS: String = "http://calincapitanu.com/"

    constructor(context: Context){
        var layout: ConstraintLayout = View.inflate(context, R.layout.activity_login , null) as ConstraintLayout
        progressDialog = ProgressBar(context, null, android.R.attr.progressBarStyleLarge)
        var params: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(100, 100)
        params.addRule(RelativeLayout.CENTER_IN_PARENT)
        layout.addView(progressDialog, params)

    }

    fun storeUserDataInBackground(user: User, callback: GetUserCallback) {
        progressDialog.visibility = View.VISIBLE
        StoreUserDataAsyncTask(user, callback).execute()


    }

    fun fetchUserDataInBackground(user: User, callback: GetUserCallback) {
        progressDialog.visibility = View.VISIBLE
        FetchUserDataAsyncTask(user, callback).execute()


    }

    inner class StoreUserDataAsyncTask(user: User, callback: GetUserCallback) : AsyncTask<Void, Void, Void>() {

        var user: User = user
        var userCallback: GetUserCallback = callback

        override fun doInBackground(vararg params: Void?): Void? {



            var url = URL("http://calincapitanu.com/Register.php")
            var httpConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            httpConnection.requestMethod = "POST"
            httpConnection.doOutput = true
            httpConnection.connectTimeout = CONNECTION_TIMEOUT

            Log.d("ServerDebug", "got to StoreUserDataAsyncTask.doInBackground()")


            var builder: Uri.Builder = Uri.Builder()
            builder.appendQueryParameter("username", user.username)
            builder.appendQueryParameter("email", user.email)
            builder.appendQueryParameter("password", user.password)


            var query: String = builder.build().encodedQuery as String

            var os: OutputStream = httpConnection.outputStream
            var bf = BufferedWriter(OutputStreamWriter(os, "UTF-8"))
            bf.write(query)
            bf.flush()
            bf.close()

            Log.d("ServerDebug", httpConnection.responseCode.toString())

            var response = httpConnection.responseCode
            if(response != HttpURLConnection.HTTP_OK)
                throw Exception("THE RESPONSE WAS NOT GOOD!!")

            httpConnection.disconnect()

            return null
        }

        override fun onPostExecute(result: Void?) {

            userCallback.done(null)
            progressDialog.visibility = View.INVISIBLE

            super.onPostExecute(result)


        }
    }


    inner class FetchUserDataAsyncTask(user: User, callback: GetUserCallback) : AsyncTask<Void, Void, User>() {

        var user: User = user
        var userCallback: GetUserCallback = callback


        override fun doInBackground(vararg params: Void?): User {


            var builder: Uri.Builder = Uri.Builder()
            builder.appendQueryParameter("email", user.email)
            builder.appendQueryParameter("password", user.password)

            var query: String = builder.build().encodedQuery as String

            var returnedUser: User



            var url: URL = URL("http://calincapitanu.com/FetchUserData.php")
            var httpConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            httpConnection.requestMethod = "POST"
            httpConnection.doOutput = true
            httpConnection.doInput = true
            httpConnection.connectTimeout = CONNECTION_TIMEOUT


            var os: OutputStream = httpConnection.outputStream
            var bf = BufferedWriter(OutputStreamWriter(os, "UTF-8"))
            bf.write(query)
            bf.flush()
            bf.close()


            Log.d("ServerDebug",httpConnection.responseCode.toString())
            Log.d("ServerDebug",httpConnection.responseMessage.toString())

            var inputStream = httpConnection.inputStream
            var bufferedReader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            var jObject: JSONObject = JSONObject(bufferedReader.readLine())


            if(jObject.length() != 0){
                Log.v("happened", "2")
                var username: String = jObject.getString("username")

                returnedUser = User(username,user.email,user.password)
            }
            else throw Exception("JOBJECT FAULT")

            return returnedUser
        }

        override fun onPostExecute(returnedUser: User) {

            userCallback.done(returnedUser)
            progressDialog.visibility = View.INVISIBLE



            super.onPostExecute(returnedUser)


        }

    }


}