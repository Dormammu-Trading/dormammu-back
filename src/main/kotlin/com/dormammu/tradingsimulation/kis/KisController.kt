package com.dormammu.tradingsimulation.kis

import com.dormammu.tradingsimulation.kis.domain.KisApiUrl
import com.dormammu.tradingsimulation.config.KisApiEnvConfig
import com.fasterxml.jackson.databind.ObjectMapper
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.reactive.function.BodyInserters
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
        val kisApiKey = kisApiEnvConfig.getKisApiKey()
        val kisSecretApiKey = kisApiEnvConfig.getKisSecretApiKey()
        val result = HashMap<String, Any>()
        var jsonInString = ""
        try {
            val bodyMap = HashMap<String, String>()
            bodyMap.put("grant_type", "client_credentials")
            bodyMap.put("appkey", kisApiKey)
            bodyMap.put("appsecret", kisSecretApiKey)


            // POST 요청 보내기
            val response = webClient.post()
                .uri(KisApiUrl.API_TOKEN.url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(bodyMap))
                .retrieve()
                .bodyToMono(ApiToken::class.java)
                .block()


            logger.info { "response accessToken: ${response?.accessToken},  access_token_token_expired: ${response?.access_token_token_expired} "   }



//            result.put("statusCode", resultMap.statusCode)
//            result.put("header", resultMap.headers)
//            resultMap.body?.let { result.put("body", it) }

            val mapper = ObjectMapper()
            jsonInString = mapper.writeValueAsString(response)

        } catch (e: Exception) {
            when (e) {
                is HttpClientErrorException, is HttpServerErrorException -> {
                    println(e.toString())
                }else ->{
                    println(e.toString())
                }
            }
        }

        return "result body: $jsonInString"
    }

}