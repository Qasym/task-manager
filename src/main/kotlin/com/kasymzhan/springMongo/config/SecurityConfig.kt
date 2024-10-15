package com.kasymzhan.springMongo.config

import com.kasymzhan.springMongo.services.CustomUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(val userDetailsService: CustomUserDetailsService) {
    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { it.disable() }.authorizeHttpRequests { auth ->
                auth.requestMatchers("/register", "/login", "/").permitAll()
                    .anyRequest().authenticated()
            }.formLogin { form ->
                form.loginPage("/login").permitAll()
            }.logout { logout ->
                logout.permitAll()
            }
        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        println("userDetailsService called")
        val userList = userDetailsService.userRepository.findAll()
        val userDetailsList = mutableListOf<UserDetails>()
        for (user in userList) {
            userDetailsList.add(User.withUsername(user.username)
                .password(user.password)
                .roles(user.roles.first())
                .build())
        }
        return InMemoryUserDetailsManager(userDetailsList)
    }
}