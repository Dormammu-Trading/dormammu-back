package com.dormammu.tradingsimulation.kis.foreign.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ForeignStockTermTradedPriceResponse(
    val rtCd: String,
    val msgCd: String,
    val msg1: String,
    val output1: ForeignStockTermTradedPriceCommonInfo,
    val output2: List<ForeignStockTermTradedPrice>
) {

}
