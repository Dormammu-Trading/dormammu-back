package com.dormammu.tradingsimulation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TradingSimulationApplication

fun main(args: Array<String>) {
	runApplication<TradingSimulationApplication>(*args)
}
