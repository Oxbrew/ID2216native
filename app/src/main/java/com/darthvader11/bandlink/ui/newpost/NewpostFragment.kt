package com.darthvader11.bandlink.ui.newpost


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.darthvader11.bandlink.Objects.Post
import com.darthvader11.bandlink.Objects.UserLocalStore
import com.darthvader11.bandlink.R
import com.darthvader11.bandlink.models.Feed
import com.darthvader11.bandlink.server.ServerRequest
import com.darthvader11.bandlink.ui.feed.FeedFragment
import com.google.android.material.textfield.TextInputEditText

class NewpostFragment : Fragment(), View.OnClickListener {

    lateinit var post: Post
    lateinit var title: TextInputEditText
    lateinit var description: TextInputEditText
    lateinit var location: TextInputEditText
    lateinit var author: String

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_newpost, container, false)

        val uploadImage : Button = root.findViewById(R.id.uploadImage)
        uploadImage.setOnClickListener(this)
        val submit: Button = root.findViewById(R.id.btnSubmit)
        submit.setOnClickListener(this)

        val userbase = UserLocalStore(context!!)
        val user = userbase.getLoggedInUser()

         title= root.findViewById(R.id.inputTitle)
         author = user.username
         description = root.findViewById(R.id.inputDescription)
         location= root.findViewById(R.id.inputLocation)




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
            R.id.btnSubmit -> {
                post = Post(title.text.toString(), author , description.text.toString(),location.text.toString())
                Log.v("newpost", title.text.toString())
                Log.v("newpost", description.text.toString())
                Log.v("newpost", location.text.toString())
                Log.v("newpost", "At least something?")
                var serverRequest = ServerRequest(context!!,R.layout.fragment_newpost)
                serverRequest.submitPost(post)
                Toast.makeText(context,"Post has been uploaded!", Toast.LENGTH_SHORT).show()
                val manager: FragmentManager? = fragmentManager
                val transaction: FragmentTransaction? = manager?.beginTransaction()
                transaction?.replace(R.id.nav_host_fragment, FeedFragment(), FeedFragment::class.java.simpleName)
                transaction?.addToBackStack(null)
                transaction?.commit()



            }

        }
    }
}