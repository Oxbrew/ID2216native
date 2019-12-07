package com.darthvader11.bandlink.server


import android.graphics.Bitmap
import com.darthvader11.bandlink.Objects.User

interface GetPostCallback {

    fun done(returnedImage: Bitmap?)


}