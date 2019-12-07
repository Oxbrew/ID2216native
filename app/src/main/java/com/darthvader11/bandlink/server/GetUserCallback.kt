package com.darthvader11.bandlink.server

import com.darthvader11.bandlink.Objects.User

interface GetUserCallback {

    fun done(returnedUser: User?)


}