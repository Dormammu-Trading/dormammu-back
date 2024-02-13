package com.dormammu.tradingsimulation.kis.foreign.service

import com.dormammu.tradingsimulation.kis.constant.KisApiUrl
import com.dormammu.tradingsimulation.kis.foreign.domain.StockInfo
import com.dormammu.tradingsimulation.kis.foreign.dto.ForeignStockCurrentTradedPriceRequest
import com.dormammu.tradingsimulation.kis.foreign.dto.ForeignStockCurrentTradedPriceResponse
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient


private val logger = KotlinLogging.logger {}

@Service
class KisForeignStockCurrentPriceService(
    private val webClient: WebClient
) {

    fun getForeignStockCurrentPrice(stockInfo: StockInfo): ForeignStockCurrentTradedPriceResponse? {

        logger.info { "stockInfo : $stockInfo "}

        val stockInfo = ForeignStockCurrentTradedPriceRequest(
            "",
            stockInfo.EXCD,
            stockInfo.SYMB
        )

        val additionalHeader = HttpHeaders().apply{
            set("tr_id", KisApiUrl.GET_CURRENT_TRADED_PRICE.transatcionId)
        }

        val foreignStockCurrentTradedPrice = webClient.get()
            .uri { builder ->
                builder.path(KisApiUrl.GET_CURRENT_TRADED_PRICE.url)
                    .queryParam("AUTH", stockInfo.AUTH)
                    .queryParam("EXCD", stockInfo.EXCD)
                    .queryParam("SYMB", stockInfo.SYMB)
                    .build()
            }.headers{ headers -> headers.addAll(additionalHeader)}
            .retrieve()
            .bodyToMono(ForeignStockCurrentTradedPriceResponse::class.java)
            .block()

        logger.info { "foreignStockCurrentTradedPrice : $foreignStockCurrentTradedPrice "}

        return foreignStockCurrentTradedPrice
    }


}