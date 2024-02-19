package com.dormammu.tradingsimulation.kis.foreign.domain

import com.dormammu.tradingsimulation.kis.foreign.dto.ForeignStockCurrentTradedPriceRequest
import com.dormammu.tradingsimulation.kis.foreign.dto.StockInfoWithSearchOption

data class StockInfo(
    val exchangeCode: String,
    val symbol: String,
    val searchOption: StockInfoWithSearchOption?
) {

    fun getForeignStockCurrentTradedPriceRequest(): ForeignStockCurrentTradedPriceRequest =
        ForeignStockCurrentTradedPriceRequest(
            "",
            exchangeCode,
            symbol
        )

}
