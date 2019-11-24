package com.darthvader11.bandlink.models

import com.darthvader11.bandlink.R

data class Feed(var postTitle: String, var postInfo: String, var postPic: Int){

}

object feedSupplier{
    val feedContent = mutableListOf(
        Feed("Looking for Drums for upcoming gig!", "John Appleseed | 8 Likes | Indie", R.drawable.drums),
        Feed("Looking for Female Vocalist","Anne Cherrytree | 50+ Likes | Pop", R.drawable.femalevocalist),
        Feed("Looking for Guitarist","Ikrisband | 300+ Likes | Pop-Rock",R.drawable.post),
        Feed("Looking for Lead Singer","Wayne Rodney | 100+ Likes | Pop",R.drawable.comm4),
        Feed("Looking for Triangle Player","Xavier Yalip | 3 Likes | Classical",R.drawable.triangle),
        Feed("Looking for Beatboxer","Jonah Relie | 50+ Likes | Hiphop",R.drawable.beatboxer),
        Feed("GIG SPOT FRIDAY NIGHT","Sailors Tavern | 100+ Likes | GIG",R.drawable.tavern),
        Feed("Looking for Bass Player","Otis Ford | 50+ Likes | Jazz", R.drawable.bass)
    )
}

