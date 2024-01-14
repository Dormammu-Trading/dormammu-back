package com.dormammu.tradingsimulation.system

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

    fun getKisApiKey(): String = kisApiKey

    fun getKisSecretApiKey(): String = kisSecretApiKey

}