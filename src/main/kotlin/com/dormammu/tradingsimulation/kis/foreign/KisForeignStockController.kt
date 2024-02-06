package com.dormammu.tradingsimulation.kis.foreign

import com.dormammu.tradingsimulation.kis.constant.KisApiUrl
import com.dormammu.tradingsimulation.kis.foreign.domain.ForeignStockCurrentTradedPrice
import com.dormammu.tradingsimulation.kis.foreign.domain.StockInfo
import com.fasterxml.jackson.databind.ObjectMapper
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
    private val objectMapper: ObjectMapper,
    private val webClient: WebClient
){
    @PostMapping("/price")
    fun getForeignStockCurrentTradedPrice(@RequestBody stockInfo: StockInfo): ForeignStockCurrentTradedPrice? {
        logger.info { "stockInfo : $stockInfo "}

        val foreignStockCurrentTradedPrice = webClient.post()
            .uri(KisApiUrl.CURRENT_TRADED_PRICE.url)
            .bodyValue(stockInfo)
            .retrieve()
            .bodyToMono(String::class.java)
            .block()

        logger.info { "foreignStockCurrentTradedPrice : $foreignStockCurrentTradedPrice "}

        return objectMapper.readValue(foreignStockCurrentTradedPrice, ForeignStockCurrentTradedPrice::class.java)
    }

}