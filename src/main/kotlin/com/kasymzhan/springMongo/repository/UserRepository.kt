package com.kasymzhan.springMongo.repository

import com.kasymzhan.springMongo.model.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String> {
    fun findByUsername(username: String): User?
}
