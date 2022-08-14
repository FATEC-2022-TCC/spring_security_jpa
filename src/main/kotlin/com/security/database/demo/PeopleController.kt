package com.security.database.demo

import com.security.database.demo.security.JwtData
import com.security.database.demo.security.JwtTokenBuilder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PeopleController(
    private val peopleRepository: PeopleRepository,
    private val userDetailsService: UserDetailsService,
    private val jwtTokenBuilder: JwtTokenBuilder
) {
    @GetMapping
    fun helloWorld() = "Hello World"

    @GetMapping("/people")
    fun getPeople() =
        peopleRepository.findAll()

    @PostMapping("/people")
    fun postPeople(@RequestBody people: People) =
        peopleRepository.save(people)

    @GetMapping("/generate")
    fun generateToken(): JwtData {
        val jwt = jwtTokenBuilder.generateToken("doni", "1234")
        return JwtData(jwt)
    }

    @GetMapping("/parse")
    fun parseToken(@RequestParam token: String): Boolean {
        return jwtTokenBuilder.parseToken(token)
    }
}