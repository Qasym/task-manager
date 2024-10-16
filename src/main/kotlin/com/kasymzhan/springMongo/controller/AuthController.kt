package com.kasymzhan.springMongo.controller

import com.kasymzhan.springMongo.model.User
import com.kasymzhan.springMongo.repository.UserRepository
import org.springframework.http.MediaType
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class AuthController(val userRepository: UserRepository, val passwordEncoder: BCryptPasswordEncoder) {
    @GetMapping("/register")
    fun openRegistration(): String {
        return "register_page"
    }

    @PostMapping("/register", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun registerUser(@RequestBody user: User): String {
        println("saving user: $user")
        user.password = passwordEncoder.encode(user.password)
        userRepository.save(user)
        println("userRepository.size = ${userRepository.count()}")
        return "redirect:/login"
    }

    @GetMapping("/login")
    fun openLogin(): String {
        return "login_page"
    }
}