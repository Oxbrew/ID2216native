package com.darthvader11.bandlink.models

import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.darthvader11.bandlink.R


data class Comment(var title: String, var comment: String, var drb: Int) {

}

object Supplier {
    val comments = mutableListOf(
        Comment("@ShredGod#1322", "FIRST!", R.drawable.comm1),
        Comment("MarkHolcomb", "Dude, check out my PRS Custom Guitar" , R.drawable.comm2),
        Comment("MargheritaVocalSinger", "What about a female vocal?", R.drawable.comm4),
        Comment("IkrisBand", "Guys, anything relevant to this post?", R.drawable.comm3),
        Comment("RimaisAmirinversed", "Pop-rock is dead bruh...", R.drawable.comm5)
    )
}
