package com.kasymzhan.springMongo

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping
class IndexController {
    @GetMapping
    fun index(): String {
        return "welcome"
    }

    @GetMapping("/register")
    fun openRegistration(): String {
        return "register_page"
    }

    @GetMapping("/login")
    fun openLogin(): String {
        return "login_page"
    }
}