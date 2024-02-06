package com.dormammu.tradingsimulation.kis

import com.dormammu.tradingsimulation.config.KisApiEnvConfig
import com.dormammu.tradingsimulation.kis.constant.KisApiUrl
import com.dormammu.tradingsimulation.kis.domain.ApiTokenRequest
import com.dormammu.tradingsimulation.kis.domain.ApiTokenResponse
import com.fasterxml.jackson.databind.ObjectMapper
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.reactive.function.client.WebClient

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/kis")
class KisController(
    private val kisApiEnvConfig: KisApiEnvConfig,
    private val webClient: WebClient
) {
    @GetMapping("/api-token")
    fun getApiToken(): String {
        var jsonInString = ""
        try {
            val body = ApiTokenRequest(
                "client_credentials",
                kisApiEnvConfig.getKisApiKey(),
                kisApiEnvConfig.getKisSecretApiKey()
            )
            logger.info { "api token 발급 시작"   }

            // POST 요청 보내기
            val response = webClient.post()
                .uri(KisApiUrl.GET_API_TOKEN.url)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(ApiTokenResponse::class.java)
                .block()


            logger.info { "response accessToken: ${response?.approvalKey}"   }

            val mapper = ObjectMapper()
            jsonInString = mapper.writeValueAsString(response)

            response?.let { kisApiEnvConfig.setKisToken(it.approvalKey) }

        } catch (e: Exception) {
            when (e) {
                is HttpClientErrorException, is HttpServerErrorException -> {
                    println("http error 인 경우: $e")
                }else ->{
                    println("http error가 아닌 경우: $e")
                }
            }
        }


        return "result body: $jsonInString"
    }

}