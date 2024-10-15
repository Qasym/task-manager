package com.kasymzhan.springMongo.model

import org.springframework.data.annotation.Id

data class User(
    @Id
    val username: String,
    var password: String,
) {
    var roles = mutableListOf("user")
}
