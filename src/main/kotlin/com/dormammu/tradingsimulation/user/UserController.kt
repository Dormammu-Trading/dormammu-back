package com.dormammu.tradingsimulation.user

import com.dormammu.tradingsimulation.system.EnvValueConfig
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(private val envValueConfig: EnvValueConfig) {

    @GetMapping("/info")
    fun getInfo(): String {
        val dbUrl = envValueConfig.getDbUrl()
        val dbUserName = envValueConfig.getDbUserName()
        val dbPassword = envValueConfig.getDbPassword()

        return "DB URL: $dbUrl, DB USERNAME : $dbUserName, DB PASSWORD: $dbPassword"
    }
}