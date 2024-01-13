package com.dormammu.tradingsimulation.system

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("classpath:env.properties")
class EnvValueConfig {

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