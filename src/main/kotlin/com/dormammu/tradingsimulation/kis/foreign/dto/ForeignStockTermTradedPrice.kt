package com.dormammu.tradingsimulation.kis.foreign.dto

data class ForeignStockTermTradedPrice(
    val xymd: String,
    val clos: String,
    val sign: String,
    val diff: String,
    val rate: String,
    val open: String,
    val high: String,
    val low: String,
    val tvol: String,
    val tamt: String,
    val pbid: String,
    val vbid: String,
    val pask: String,
    val vask: String,
)