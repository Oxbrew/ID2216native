package com.darthvader11.bandlink.data.model

import java.time.LocalDateTime

data class UserMessage (val message: String, val sender: User, val createdAt: LocalDateTime)