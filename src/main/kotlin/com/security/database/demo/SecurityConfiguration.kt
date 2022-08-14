package com.security.database.demo

import com.security.database.demo.security.JwtTokenFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfiguration(
    private val jwtTokenFilter: JwtTokenFilter
) {
    @Bean
    fun securityFilterChain(
        http: HttpSecurity
    ): SecurityFilterChain = http
        .cors().and().csrf().disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers("/people", "/generate", "/parse*").permitAll()
        .antMatchers("/*").denyAll()
        .and()
        .addFilterAt(jwtTokenFilter, UsernamePasswordAuthenticationFilter::class.java)
        .build()

    @Bean
    fun webSecurityCustomizer() = WebSecurityCustomizer {

    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}