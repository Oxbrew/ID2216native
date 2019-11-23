package com.darthvader11.bandlink.models

import com.darthvader11.bandlink.R


data class Search(var title: String, var author: String,var location: String, var drb: Int) {

}

object Supplier2 {
    val searchResults = mutableListOf(
        Search("Looking for jazz guitarist", "@Sabaton", "Helsinki, Finland", R.drawable.post),
        Search("Looking from drummer", "@Abbath", "Monaco", R.drawable.drums)
    )
}
