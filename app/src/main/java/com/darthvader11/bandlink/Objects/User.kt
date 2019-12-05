package com.darthvader11.bandlink.Objects


class User(var username: String, var email: String, var password: String) {

    constructor(email: String,password: String) : this("", email, password)




}