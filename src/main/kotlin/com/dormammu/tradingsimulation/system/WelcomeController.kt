package com.dormammu.tradingsimulation.system

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WelcomeController(
    private val dbEnvConfig: DbEnvConfig,
    private val kisApiEnvConfig: KisApiEnvConfig
) {
    @GetMapping("/")
    fun welcome(): String = "welcome"

    @GetMapping("/db-env")
    fun getEnvValueInfo(): String {
        val dbUrl = dbEnvConfig.getDbUrl()
        val dbUserName = dbEnvConfig.getDbUserName()
        val dbPassword = dbEnvConfig.getDbPassword()

        return "DB URL: $dbUrl, DB USERNAME : $dbUserName, DB PASSWORD: $dbPassword"
    }

    @GetMapping("/kis-api")
    fun getKisApiInfo(): String {
        val kisApiKey = kisApiEnvConfig.getKisApiKey()
        val kisSecretApiKey = kisApiEnvConfig.getKisSecretApiKey()

        return "KIS_API_KEY: $kisApiKey, KIS_SECRET_API_KEY : $kisSecretApiKey"
    }

}