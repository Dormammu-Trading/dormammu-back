package com.dormammu.tradingsimulation.config

import io.github.oshai.kotlinlogging.KotlinLogging
import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.DefaultUriBuilderFactory
import reactor.netty.Connection
import reactor.netty.http.client.HttpClient
import reactor.netty.resources.ConnectionProvider
import java.time.Duration
import java.util.concurrent.TimeUnit

private val logger = KotlinLogging.logger {}

@Configuration
class WebClientConfig {

    val factory: DefaultUriBuilderFactory = DefaultUriBuilderFactory()
    val httpClient: HttpClient = HttpClient.create()
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000); // 10초

    @Bean
    fun webClient(): WebClient = WebClient.builder()
        .clientConnector(
            ReactorClientHttpConnector(HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                .doOnConnected { connection: Connection ->
                    connection.addHandlerLast(ReadTimeoutHandler(10000, TimeUnit.MILLISECONDS))
                    connection.addHandlerLast(WriteTimeoutHandler(10000, TimeUnit.MILLISECONDS))
                })
        )

        .build()

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