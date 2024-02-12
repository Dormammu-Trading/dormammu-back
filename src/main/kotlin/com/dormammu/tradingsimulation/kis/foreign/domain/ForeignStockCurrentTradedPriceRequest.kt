package com.dormammu.tradingsimulation.kis.foreign.domain

data class ForeignStockCurrentTradedPriceRequest(
    val AUTH: String,
    val EXCD: String,
    val SYMB: String
)