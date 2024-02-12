package com.dormammu.tradingsimulation.config

import com.dormammu.tradingsimulation.config.dto.KisApiEnv
import com.dormammu.tradingsimulation.kis.constant.KisApiUrl
import com.dormammu.tradingsimulation.kis.domain.ApiTokenRequest
import com.dormammu.tradingsimulation.kis.domain.ApiTokenResponse
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient

private val logger = KotlinLogging.logger {}

@Configuration()
@PropertySource("classpath:env.properties")
class KisApiEnvConfig() {

    @Bean(name = ["KisApiToken"])
    fun KisApiEnv(): KisApiEnv  {
        logger.info { "Kis Api Config Post init" }

        val webClient: WebClient = WebClient.create()
        lateinit var token: String
        val body = ApiTokenRequest(
            "client_credentials",
            getKisApiKey(),
            getKisSecretApiKey()
        )

        // POST 요청 보내기
        val response = webClient.post()
            .uri(KisApiUrl.GET_API_TOKEN.url)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(body)
            .retrieve()
            .bodyToMono(ApiTokenResponse::class.java)
            .block()

        if (response != null) {
            token = response.approvalKey
            setKisToken(token)
        }
        logger.info { "KIS API TOKEN 발급 Success $token" }
        return KisApiEnv(kisApiKey, kisSecretApiKey, token)
    }

    @Value("\${KIS_API_KEY}")
    private lateinit var kisApiKey: String

    @Value("\${KIS_SECRET_API_KEY}")
    private lateinit var kisSecretApiKey: String

    private lateinit var _kisToken: String


    fun getKisApiKey(): String = kisApiKey

    fun getKisSecretApiKey(): String = kisSecretApiKey

    val kisToken: String
        get() = _kisToken

    fun setKisToken(kisToken: String) {
        _kisToken = kisToken
    }

}