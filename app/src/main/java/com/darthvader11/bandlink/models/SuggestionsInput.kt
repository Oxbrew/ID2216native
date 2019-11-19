package com.darthvader11.bandlink.models

import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.darthvader11.bandlink.R


data class Suggestion(var title: String, var suggestion: String, var drb: Int) {

}


object Supplier2 {
    val suggestions = listOf(
        Suggestion("Nita Strauss", "I definitely recommend: @Toro", R.drawable.sugg1),
        Suggestion("Jared Dines", "HahahahHAaha, go for @StevieT" , R.drawable.sugg2),
        Suggestion("Stevie Terryberry", "@JaredDines , ye because I can DJ0NT and it seems like you can't", R.drawable.sugg3)
    )
}