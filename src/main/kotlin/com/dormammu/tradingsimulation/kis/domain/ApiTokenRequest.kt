package com.dormammu.tradingsimulation.kis.domain

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ApiTokenRequest(
    var grantType: String,
    var appkey: String,
    var secretKey: String,
){
}
