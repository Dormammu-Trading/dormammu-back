package com.dormammu.tradingsimulation.kis.foreign.domain

import com.dormammu.tradingsimulation.kis.foreign.dto.ForeignStockCurrentTradedPriceRequest

data class StockInfo(
    val exchangeCode: String,
    val symbol: String,
) {

    fun getForeignStockCurrentTradedPriceRequest(): ForeignStockCurrentTradedPriceRequest =
        ForeignStockCurrentTradedPriceRequest(
            "",
            exchangeCode,
            symbol
        )

}
