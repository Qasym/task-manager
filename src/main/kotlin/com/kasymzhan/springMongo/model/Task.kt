package com.kasymzhan.springMongo.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "tasks_list")
data class Task(
    @Id
    var id: ObjectId? = null,
    var title: String,
    var description: String = "",
    var isDone: Boolean = false,
)
