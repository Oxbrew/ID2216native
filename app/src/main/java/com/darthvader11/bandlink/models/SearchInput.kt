package com.darthvader11.bandlink.models

import android.graphics.Bitmap
import com.darthvader11.bandlink.R


data class Search(var title: String, var author: String,var location: String, var image: Bitmap?) {

}

object Supplier2 {
    val searchResults = mutableListOf<Feed>(
        //Search("Looking for jazz guitarist", "@Sabaton", "Helsinki, Finland", R.drawable.post),
        //Search("Looking from drummer", "@Abbath", "Monaco", R.drawable.drums)
    )
}
