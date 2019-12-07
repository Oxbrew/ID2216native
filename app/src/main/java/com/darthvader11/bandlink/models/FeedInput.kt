package com.darthvader11.bandlink.models

import android.graphics.Bitmap
import com.darthvader11.bandlink.R

data class Feed(var postTitle: String, var author: String, var likes: Int, var genre: String, var postPic: Bitmap?){

}

object feedSupplier{
    val feedContent = mutableListOf<Feed>(
        //Feed("Looking for Drums for upcoming gig!", "John Appleseed",  8,  "Indie", R.drawable.drums)
    )
}

