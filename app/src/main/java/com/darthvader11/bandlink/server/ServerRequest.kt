package com.darthvader11.bandlink.server

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.darthvader11.bandlink.Objects.Post
import com.darthvader11.bandlink.Objects.User
import com.darthvader11.bandlink.models.Feed
import com.darthvader11.bandlink.models.feedSupplier
import org.json.JSONArray
import org.json.JSONObject
import java.io.*
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class ServerRequest() {

    lateinit var progressDialog: ProgressBar
    val CONNECTION_TIMEOUT: Int = 1000 * 15

    constructor(context: Context, layout: Int) : this(){
        var layout: ConstraintLayout = View.inflate(context,layout , null) as ConstraintLayout
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
    fun submitPost(post: Post){
        progressDialog.visibility = View.VISIBLE
        SubmitPostAsyncTask(post).execute()
    }

    fun downloadImage(name: String, callback: GetPostCallback){
        progressDialog.visibility = View.VISIBLE
        DownloadImageAsyncTask(name, callback).execute()
    }

    fun updateAllFeed(feedCallback: GetFeedCallback){
        FetchAllPostsAsyncTask(feedCallback).execute()
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
                Log.v("TEST", jObject.toString())

                returnedUser = User(
                    username,
                    user.email,
                    user.password
                )
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

    inner class SubmitPostAsyncTask(post: Post) : AsyncTask<Void, Void, Void>() {

        var post: Post = post


        override fun doInBackground(vararg params: Void?): Void? {


            var byteArrayOutputStream = ByteArrayOutputStream()
            post.image.compress(Bitmap.CompressFormat.JPEG, 100 , byteArrayOutputStream)
            var encodedImage: String = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT)

            var url = URL("http://calincapitanu.com/UploadPost.php")
            var httpConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            httpConnection.requestMethod = "POST"
            httpConnection.doOutput = true
            httpConnection.connectTimeout = CONNECTION_TIMEOUT

            Log.d("ServerDebug", "got to SubmitPostDataAsyncTask.doInBackground()")


            var builder: Uri.Builder = Uri.Builder()
            builder.appendQueryParameter("Title", post.title)
            builder.appendQueryParameter("Author", post.author)
            builder.appendQueryParameter("Description", post.description)
            builder.appendQueryParameter("Location", post.location)
            builder.appendQueryParameter("Picture", encodedImage)

            Log.v("Server", encodedImage)
            Log.v("Server", encodedImage.length.toString())


            var query: String = builder.build().encodedQuery as String

            var os: OutputStream = httpConnection.outputStream
            var bf = BufferedWriter(OutputStreamWriter(os, "UTF-8"))
            bf.write(query)
            bf.flush()
            bf.close()

            Log.d("ServerDebug", httpConnection.responseCode.toString())

            var response = httpConnection.responseCode
            if (response != HttpURLConnection.HTTP_OK)
                throw Exception("THE RESPONSE WAS NOT GOOD!!")

            httpConnection.disconnect()

            return null
        }

        override fun onPostExecute(result: Void?) {
            progressDialog.visibility = View.INVISIBLE
            super.onPostExecute(result)


        }
    }

    inner class DownloadImageAsyncTask(name: String, callback: GetPostCallback) : AsyncTask<Void, Void, Bitmap>() {

        var name: String = name
        var postCallback: GetPostCallback = callback


        override fun doInBackground(vararg params: Void?): Bitmap? {



            var url = URL("http://calincapitanu.com/pictures/" + name + ".JPG")
            var httpConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            httpConnection.connectTimeout = CONNECTION_TIMEOUT
            httpConnection.readTimeout = CONNECTION_TIMEOUT


            Log.v("ServerDebug", httpConnection.responseCode.toString())

            return BitmapFactory.decodeStream(httpConnection.getContent() as InputStream, null, null)

        }

        override fun onPostExecute(result: Bitmap){
            progressDialog.visibility = View.INVISIBLE
            postCallback.done(result)
            super.onPostExecute(result)
        }
    }

    inner class FetchAllPostsAsyncTask(feedCallback: GetFeedCallback) : AsyncTask<Void, Void, JSONArray>() {

        var feedCallback: GetFeedCallback = feedCallback

        override fun doInBackground(vararg params: Void?): JSONArray {



            var url: URL = URL("http://calincapitanu.com/FetchAllPosts.php")
            var httpConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            httpConnection.requestMethod = "POST"
            httpConnection.doOutput = true
            httpConnection.doInput = true
            httpConnection.connectTimeout = CONNECTION_TIMEOUT


            //Log.d("ServerDebug responseCode",httpConnection.responseCode.toString())
            //Log.d("ServerDebug respondeMessage",httpConnection.responseMessage.toString())

            var inputStream = httpConnection.inputStream
            var bufferedReader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            var jArray = JSONArray(bufferedReader.readLine())

            Log.v("JSONArray", jArray.toString())
            Log.v("JSONArray length", jArray.length().toString())


/*
            for(i in 0 until jArray.length()){
                Log.v("jobject",jArray[i].toString())
                var jObject: JSONObject = jArray[i] as JSONObject

                downloadImage(jObject.getString("Title"), object: GetPostCallback{
                    override fun done(returnedImage: Bitmap?) {
                        if(returnedImage != null) {
                            feedSupplier.feedContent.add(
                                Feed(
                                    jObject.getString("Title"),
                                    "@" + jObject.getString("Author"),
                                    jObject.getInt("LikesCount"),
                                    jObject.getString("Genre"),
                                    returnedImage
                                )
                            )
                            Log.v("shouldBeAfterFeed", feedSupplier.feedContent.size.toString())
                            Log.v("added", "one feed item has been added")
                        }
                    }
                })

                Log.v("download", jObject.getString("Title"))
            }
            Log.v("JARRAY", jArray.length().toString())*/
            return jArray
        }

        override fun onPostExecute(result: JSONArray){
            progressDialog.visibility = View.INVISIBLE
            feedCallback.done(result)
            //Log.v("shouldBeAfterFeed", feedSupplier.feedContent.size.toString())
            Log.v("JSONArray", "Array has been fetched")
            super.onPostExecute(result)

        }

    }





}