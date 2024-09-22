package com.kasymzhan.springMongo.model

import org.springframework.data.annotation.Id

data class Task(
    @Id
    val id: Int,
    var title: String,
    var description: String,
    var isDone: Boolean,
)
