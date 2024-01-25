package com.dormammu.tradingsimulation.config

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.codec.ClientCodecConfigurer
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.netty.resources.ConnectionProvider
import java.time.Duration
import java.util.*

private val logger = KotlinLogging.logger {}

@Configuration
class WebClientConfig {
    val objectMapper = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .registerModule(JavaTimeModule())


    @Bean(name = ["sampleWebClient"])
    fun webClient(): WebClient? {
        return WebClient
            .builder()
            .baseUrl("http://localhost:8080")
            .codecs { configurer: ClientCodecConfigurer ->
                configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024)
            }
            .filter(ExchangeFilterFunction.ofRequestProcessor { clientRequest ->
                val servletRequestAttributes: ServletRequestAttributes =
                    RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes
                logger.info{ "####" + Thread.currentThread().name}
                logger.info { clientRequest.url().toString() }
                val from: ClientRequest.Builder = ClientRequest.from(clientRequest)
                val e: Enumeration<*> = servletRequestAttributes.getRequest().getHeaderNames()
                while (e.hasMoreElements()) {
                    val nextHeaderName = e.nextElement() as String
                    val headerValue: String = servletRequestAttributes.getRequest().getHeader(nextHeaderName)
                    if (nextHeaderName == "caller") {
                        from.header(nextHeaderName, headerValue)
                    }
                    logger.info { nextHeaderName + headerValue }
                }
                Mono.just(from.build())
            }.andThen(ExchangeFilterFunction.ofResponseProcessor { clientResponse ->
                clientResponse.headers().asHttpHeaders().forEach { name, value ->
                    try {
                        logger.info { name.toString() + ObjectMapper().writeValueAsString(value) }
                    } catch (e: JsonProcessingException) {
                        e.printStackTrace()
                    }
                }
                Mono.just(clientResponse)
            }))
            .build()
    }

    @Bean
    fun connectionProvider(): ConnectionProvider {
        return ConnectionProvider.builder("http-pool")
            .maxConnections(100)
            .pendingAcquireTimeout(Duration.ZERO)
            .pendingAcquireMaxCount(-1)
            .maxIdleTime(Duration.ofMillis(2000L))
            .build()
    }

}