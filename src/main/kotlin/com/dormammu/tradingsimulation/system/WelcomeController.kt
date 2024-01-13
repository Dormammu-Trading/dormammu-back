package com.dormammu.tradingsimulation.system

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WelcomeController {
    @GetMapping("/")
    fun welcome(): String = "welcome"
}