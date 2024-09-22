package com.kasymzhan.springMongo.repository

import com.kasymzhan.springMongo.model.Task
import org.springframework.data.mongodb.repository.MongoRepository

interface TaskRepository : MongoRepository<Task, Int> {}