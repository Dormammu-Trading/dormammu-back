package com.dormammu.tradingsimulation.kis.constant

enum class KisApiUrl(
    val url: String
) {
    GET_API_TOKEN("https://openapi.koreainvestment.com:9443/oauth2/Approval"),
    GET_ACCESS_TOKEN("https://openapi.koreainvestment.com:9443/oauth2/tokenP"),
    CURRENT_TRADED_PRICE("https://openapi.koreainvestment.com:9443/uapi/overseas-price/v1/quotations/price")
}
