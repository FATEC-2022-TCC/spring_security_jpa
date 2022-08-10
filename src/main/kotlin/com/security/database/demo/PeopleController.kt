package com.security.database.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PeopleController(
    private val peopleRepository: PeopleRepository
) {
    @GetMapping
    fun helloWorld() = "Hello World"

    @GetMapping("/people")
    fun getPeople() =
        peopleRepository.findAll()

    @PostMapping("/people")
    fun postPeople(@RequestBody people: People) =
        peopleRepository.save(people)
}