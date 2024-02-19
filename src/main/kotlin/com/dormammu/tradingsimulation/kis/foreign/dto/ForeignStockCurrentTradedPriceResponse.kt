package com.dormammu.tradingsimulation.kis.foreign.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ForeignStockCurrentTradedPriceResponse(
    val rtCd: String,
    val msgCd: String,
    val msg1: String,
    val output: ForeignStockCurrentTradedPrice
) {
}