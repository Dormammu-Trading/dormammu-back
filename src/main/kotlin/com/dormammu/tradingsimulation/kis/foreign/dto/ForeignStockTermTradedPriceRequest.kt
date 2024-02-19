package com.dormammu.tradingsimulation.kis.foreign.dto

data class ForeignStockTermTradedPriceRequest(
    val AUTH: String, // 사용자권한정보
    val EXCD: String, // 거래소코드
    val SYMB: String, // 종목코드
    val GUBN: String, // 일/주/월구분
    val BYMD: String, // 조회기준일자
    val MODP: String, // 수정주가반영여부
    val KEYB: String, // NEXT KEY BUFF
){
}