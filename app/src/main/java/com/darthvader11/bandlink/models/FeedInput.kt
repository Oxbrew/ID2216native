package com.darthvader11.bandlink.models

import android.graphics.Bitmap

data class Feed(var postTitle: String, var author: String, var likesCount: Int,var tags: , var genre: String, var postPic: Bitmap?, var post_id: Int){
}

object feedSupplier{
    val feedContent = mutableListOf<Feed>(
        //Feed("Looking for Drums for upcoming gig!", "John Appleseed",  8,  "Indie", R.drawable.drums)
    )
}

