package com.dormammu.tradingsimulation.kis.foreign.domain

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class StockInfo(
    val AUTH: String,
    val EXCD: String,
    val SYMB: String
) {
}
