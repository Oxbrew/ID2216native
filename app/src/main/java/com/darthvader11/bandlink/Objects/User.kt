package com.darthvader11.bandlink.Objects

import android.graphics.Bitmap


class User(var username: String, var email: String, var password: String) {

    constructor(email: String,password: String) : this("", email, password)

}

