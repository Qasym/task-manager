package com.kasymzhan.springMongo.services

import com.kasymzhan.springMongo.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        if (username.isNullOrBlank())
            throw Exception("Provided username is null")
        val user = userRepository.findByUsername(username)
            ?: throw Exception("User: $username not found")
        println("user >$username< is found")
        val userDetails = User.withUsername(user.username)
            .password(user.password)
            .roles(user.roles.first())
            .build()
        return userDetails
    }
}