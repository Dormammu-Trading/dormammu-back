package com.dormammu.tradingsimulation.kis.constant

enum class KisApiUrl(
    val url: String,
    val transatcionId: String
) {
    GET_WEBSOCKET_ACCESS_KEY("https://openapi.koreainvestment.com:9443/oauth2/Approval",""),
    GET_API_TOKEN("https://openapi.koreainvestment.com:9443/oauth2/tokenP",""),
    GET_CURRENT_TRADED_PRICE(
        "/uapi/overseas-price/v1/quotations/price",
        "HHDFS00000300"
    )
}
