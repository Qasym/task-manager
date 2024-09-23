package com.kasymzhan.springMongo.model

import org.springframework.data.annotation.Id

data class Task(
    @Id
    var id: Long = 0,
    var title: String,
    var description: String = "",
    var isDone: Boolean = false,
)
