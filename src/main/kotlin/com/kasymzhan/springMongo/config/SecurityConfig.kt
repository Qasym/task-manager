package com.kasymzhan.springMongo.config

import com.kasymzhan.springMongo.services.CustomUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Role
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(val userDetailsService: CustomUserDetailsService) {
    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            authorizeRequests {
                authorize("/", permitAll)
                authorize("/login", permitAll)
                authorize("/register", permitAll)
                authorize("/tasks", hasRole(Roles.USER))
                authorize("/tasks/**", hasRole(Roles.USER))
            }
            formLogin {
                loginPage = "/login"
                defaultSuccessUrl("/tasks", true)
            }
            logout {
                logoutUrl = "/logout" // ???
            }
            csrf { disable() }
        }
        return http.build()
    }
}