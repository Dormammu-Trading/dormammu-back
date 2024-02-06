package com.dormammu.tradingsimulation.config

import com.dormammu.tradingsimulation.kis.constant.KisApiUrl
import com.dormammu.tradingsimulation.kis.domain.ApiTokenRequest
import com.dormammu.tradingsimulation.kis.domain.ApiTokenResponse
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.http.MediaType
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.reactive.function.client.WebClient

private val logger = KotlinLogging.logger {}
@Configuration
@PropertySource("classpath:env.properties")
class KisApiEnvConfig(
    private val webClient: WebClient
) {

    @PostConstruct
    fun postInitialize(){
        try {
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

            logger.info { "KIS API TOKEN 발급 Success"   }

            response?.let { setKisToken(it.approvalKey) }

        } catch (e: Exception) {
            when (e) {
                is HttpClientErrorException, is HttpServerErrorException -> {
                    println("http error 인 경우: $e")
                }else ->{
                println("http error가 아닌 경우: $e")
            }
            }
        }
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

    fun setKisToken(kisToken : String) {
        _kisToken = kisToken
    }

}