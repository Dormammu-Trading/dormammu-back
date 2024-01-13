package com.dormammu.tradingsimulation.system

import io.github.cdimascio.dotenv.dotenv
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class EnvValueConfig {
    init {
        dotenv()
    }

    @Value("\${DB_URL}")
    private lateinit var dbUrl: String

    @Value("\${DB_USERNAME}")
    private lateinit var dbUserName: String

    @Value("\${DB_PASSWORD}")
    private lateinit var dbPassword: String

    fun getDbUrl(): String = dbUrl

    fun getDbUserName(): String = dbUserName

    fun getDbPassword(): String = dbPassword

}