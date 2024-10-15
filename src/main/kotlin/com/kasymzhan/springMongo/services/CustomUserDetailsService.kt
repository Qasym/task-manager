package com.kasymzhan.springMongo.services

import com.kasymzhan.springMongo.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        if (username == null)
            throw Exception("Provided username is null")
        println("username is >$username<")
        val user = userRepository.findByUsername(username)
            ?: throw Exception("User: $username not found")
        return User(
            user.username,
            user.password,
            user.roles.map { SimpleGrantedAuthority(it) })
    }
}