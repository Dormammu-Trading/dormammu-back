package com.dormammu.tradingsimulation.kis.foreign.domain

data class ForeignStockCurrentTradedPrice(
    val rsym: String,
    val zdiv: String,
    val base: String,
    val pvol: String,
    val last: String,
    val sign: String,
    val diff: String,
    val rate: String,
    val tvol: String,
    val tamt: String,
    val ordy: String
)