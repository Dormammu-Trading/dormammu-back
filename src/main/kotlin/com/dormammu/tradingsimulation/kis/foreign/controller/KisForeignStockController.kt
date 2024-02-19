package com.dormammu.tradingsimulation.kis.foreign.controller

import com.dormammu.tradingsimulation.kis.foreign.domain.StockInfo
import com.dormammu.tradingsimulation.kis.foreign.dto.ForeignStockCurrentTradedPriceResponse
import com.dormammu.tradingsimulation.kis.foreign.dto.ForeignStockTermTradedPriceResponse
import com.dormammu.tradingsimulation.kis.foreign.dto.StockInfoWithSearchOption
import com.dormammu.tradingsimulation.kis.foreign.service.KisForeignStockCurrentPriceService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger {}
@RestController
@RequestMapping("/kis/foreign-stock")
class KisForeignStockController(
    private val kisForeignStockCurrentPriceService: KisForeignStockCurrentPriceService
){
    @PostMapping("/current-price")
    fun getForeignStockCurrentTradedPrice(@RequestBody stockInfo: StockInfo): ForeignStockCurrentTradedPriceResponse? {
        logger.info { "stockInfo : $stockInfo "}

        val foreignStockCurrentTradedPrice = kisForeignStockCurrentPriceService.getForeignStockCurrentPrice(stockInfo)

        logger.info { "foreignStockCurrentTradedPrice : $foreignStockCurrentTradedPrice "}

        return foreignStockCurrentTradedPrice
    }

    @PostMapping("/term-price")
    fun getForeignStockTermTradedPrice(@RequestBody stockInfo: StockInfoWithSearchOption): ForeignStockTermTradedPriceResponse? {
        logger.info { "stockInfo : $stockInfo "}

        val foreignStockTermTradedPrice = kisForeignStockCurrentPriceService.getForeignStockTermPrice(stockInfo)

        logger.info { "foreignStockCurrentTradedPrice : $foreignStockTermTradedPrice "}

        return foreignStockTermTradedPrice
    }

}