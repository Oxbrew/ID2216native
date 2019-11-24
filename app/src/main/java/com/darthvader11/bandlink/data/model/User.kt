package com.darthvader11.bandlink.data.model

import android.media.Image
import java.util.*

data class User(val name: String, var profilePic: Image?, val id: UUID)