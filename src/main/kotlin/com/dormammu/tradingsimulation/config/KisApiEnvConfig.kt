package com.dormammu.tradingsimulation.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("classpath:env.properties")
class KisApiEnvConfig {
    @Value("\${KIS_API_KEY}")
    private lateinit var kisApiKey: String

    @Value("\${KIS_SECRET_API_KEY}")
    private lateinit var kisSecretApiKey: String

    private lateinit var _kisToken: String


    fun getKisApiKey(): String = kisApiKey

    fun getKisSecretApiKey(): String = kisSecretApiKey

    val kisToken: String
        get() = _kisToken

    fun getKisToken(): String = kisToken

    fun setKisToken(kisToken : String) {
        _kisToken = kisToken
    }

}