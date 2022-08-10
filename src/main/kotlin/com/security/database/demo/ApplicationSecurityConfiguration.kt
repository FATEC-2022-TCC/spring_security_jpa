package com.security.database.demo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfiguration {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain =
        with(http) {
            authorizeRequests {
                it.antMatchers("/people").permitAll()
                it.antMatchers("/*").denyAll()
            }
            build()
        }

    @Bean
    fun webSecurityCustomizer() = WebSecurityCustomizer {

    }
}