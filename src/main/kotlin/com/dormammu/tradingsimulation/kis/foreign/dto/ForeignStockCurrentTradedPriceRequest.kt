package com.dormammu.tradingsimulation.kis.foreign.dto

data class ForeignStockCurrentTradedPriceRequest(
    val AUTH: String,
    val EXCD: String,
    val SYMB: String
)