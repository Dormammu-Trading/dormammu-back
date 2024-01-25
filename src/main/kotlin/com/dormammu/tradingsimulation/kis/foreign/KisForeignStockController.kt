package com.dormammu.tradingsimulation.kis.foreign

import com.dormammu.tradingsimulation.config.KisApiEnvConfig
import com.dormammu.tradingsimulation.kis.foreign.domain.StockInfo
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/kis/foreign-stock")
class KisForeignStockController(
    private val kisApiEnvConfig: KisApiEnvConfig,
    private val webClient: WebClient
){
    @PostMapping("/price")
    fun getMarketPrice(@RequestBody stockInfo: StockInfo) {
        logger.info { "stockInfo : $stockInfo "}


    }

}