package com.darthvader11.bandlink.ui.register

data class RegisterFormState(
    val usernameError: Int? = null,
    val mailError: Int? = null,
    val verifyPasswordError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false

)