package com.dormammu.tradingsimulation.kis

import com.dormammu.tradingsimulation.system.KisApiEnvConfig
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponents
import org.springframework.web.util.UriComponentsBuilder

@RestController
class KisController(
    private val kisApiEnvConfig: KisApiEnvConfig
) {
    @PostMapping("/getApiToken")
    fun getApiToken(): String {
        val kisApiKey = kisApiEnvConfig.getKisApiKey()
        val kisSecretApiKey = kisApiEnvConfig.getKisSecretApiKey()
        val result = HashMap<String, Any>()
        var jsonInString = ""
        try {
            val requestFactory = HttpComponentsClientHttpRequestFactory()
            requestFactory.setConnectTimeout(5000)
            requestFactory.setConnectionRequestTimeout(5000)

            val restTemplate = RestTemplate(requestFactory)
            val header = org.springframework.http.HttpHeaders()
            val entity = HttpEntity<Map<String, Any>>(header)
            val url = "https://openapi.koreainvestment.com:9443/oauth2/tokenP"
            val uri: UriComponents = UriComponentsBuilder.fromHttpUrl(url + "?").build()

            val resultMap: ResponseEntity<Map<*, *>> =
                restTemplate.exchange(uri.toUriString(), HttpMethod.POST, entity, Map::class.java)

            result.put("statusCode", resultMap.statusCode)
            result.put("header", resultMap.headers)
            resultMap.body?.let { result.put("body", it) }

            val mapper = ObjectMapper()
            jsonInString = mapper.writeValueAsString(resultMap.body)

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