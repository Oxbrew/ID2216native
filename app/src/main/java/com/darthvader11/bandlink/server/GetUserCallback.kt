package com.darthvader11.bandlink.server

import com.darthvader11.bandlink.User

interface GetUserCallback {

    fun done(returnedUser: User?)


}