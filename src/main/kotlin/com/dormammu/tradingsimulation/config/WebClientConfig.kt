package com.dormammu.tradingsimulation.config

import io.github.oshai.kotlinlogging.KotlinLogging
import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.Connection
import reactor.netty.http.client.HttpClient
import reactor.netty.resources.ConnectionProvider
import java.time.Duration
import java.util.concurrent.TimeUnit

private val logger = KotlinLogging.logger {}


@DependsOn(value = ["KisApiToken"])
@Configuration
class WebClientConfig(
    val kisApiEnvConfig: KisApiEnvConfig
) {

    @Bean
    fun webClient(): WebClient {
        val webClient = WebClient.builder()
            .clientConnector(
                ReactorClientHttpConnector(HttpClient.create()
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                    .doOnConnected { connection: Connection ->
                        connection.addHandlerLast(ReadTimeoutHandler(10000, TimeUnit.MILLISECONDS))
                        connection.addHandlerLast(WriteTimeoutHandler(10000, TimeUnit.MILLISECONDS))
                    })
            ).defaultHeader("Authorization", "Bearer ${kisApiEnvConfig.kisToken}").build()
        logger.info { "WebClient 생성 Success" }
        return webClient
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