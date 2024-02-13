package com.dormammu.tradingsimulation.kis.domain

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ApiTokenResponse(
    val accessToken: String,
    val accessTokenTokenExpired: String,
    val tokenType: String,
    val expiresIn: Int,
){
}