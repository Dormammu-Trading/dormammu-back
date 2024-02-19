package com.dormammu.tradingsimulation.kis.foreign.dto

import com.dormammu.tradingsimulation.support.BooleanTransformer

data class StockInfoWithSearchOption(
    val exchangeCode: String,
    val symbol: String,
    val termType: String,
    val searchDate: String,
    val modifyApplyFlag: Boolean,
    val nextKeyBuff: String
) {
    fun getForeignStockTermTradedPriceRequest(): ForeignStockTermTradedPriceRequest =
        ForeignStockTermTradedPriceRequest(
            "",
            exchangeCode,
            symbol,
            termType,
            searchDate,
            BooleanTransformer.booleanToBinary(modifyApplyFlag),
            nextKeyBuff
        )

}
