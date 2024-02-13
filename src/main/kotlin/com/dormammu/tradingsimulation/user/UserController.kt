package com.dormammu.tradingsimulation.user

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val userRepository: UserRepository
    )
{

    @GetMapping("/findAll")
    fun getAllUsers(): ResponseEntity<MutableList<User>> {
        val users = userRepository.findAll()
        return ResponseEntity.ok(users)
    }
}