package com.dormammu.tradingsimulation.kis.foreign

import com.dormammu.tradingsimulation.kis.constant.KisApiUrl
import com.dormammu.tradingsimulation.kis.foreign.domain.ForeignStockCurrentTradedPriceRequest
import com.dormammu.tradingsimulation.kis.foreign.domain.ForeignStockCurrentTradedPriceResponse
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
    fun getForeignStockCurrentTradedPrice(@RequestBody stockInfo: StockInfo): ForeignStockCurrentTradedPriceResponse? {
        logger.info { "stockInfo : $stockInfo "}

        val stockInfo = ForeignStockCurrentTradedPriceRequest(
            "",
            stockInfo.EXCD,
            stockInfo.SYMB
        )

        val foreignStockCurrentTradedPrice = webClient.get()
            .uri { builder ->
                builder.path(KisApiUrl.CURRENT_TRADED_PRICE.url)
                    .queryParam("AUTH", stockInfo.AUTH)
                    .queryParam("EXCD", stockInfo.EXCD)
                    .queryParam("SYMB", stockInfo.SYMB)
                    .build()
            }
            .retrieve()
            .bodyToMono(ForeignStockCurrentTradedPriceResponse::class.java)
            .block()

        logger.info { "foreignStockCurrentTradedPrice : $foreignStockCurrentTradedPrice "}

        return foreignStockCurrentTradedPrice
    }

}