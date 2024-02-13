package com.dormammu.tradingsimulation.kis.foreign.service

import com.dormammu.tradingsimulation.kis.foreign.domain.StockInfo
import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.spring.SpringListener
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment

private val logger = KotlinLogging.logger {}

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class KisForeignStockCurrentPriceServiceTest : BehaviorSpec({

        logger.info { "given start" }






//        val webClient = mockk<WebClient>()
//        val foreignStockCurrentPriceService = KisForeignStockCurrentPriceService(webClient)
//
//        val stockInfo = StockInfo(
//            "NAS",
//            "TSLA"
//        )
//        val tslaPrice = foreignStockCurrentPriceService.getForeignStockCurrentPrice(stockInfo)
//
//        logger.info { tslaPrice }

}) {

    override fun listeners() = listOf(SpringListener)

    @Autowired
    private lateinit var kisForeignStockCurrentPriceService: KisForeignStockCurrentPriceService



    init {
        logger.info { "init start" }

        given("calculate") {
            val expression = "1 + 2"
            `when`("1과 2를 더하면") {
                val result = 1 + 2
                then("3이 반환된다") {
                    result shouldBe 3
                }
            }
        }
    }

    @Test
    fun getForeignStockCurrentPrice(){
        logger.info { "init start" }
        val stockInfo = StockInfo(

            "NAS",
            "TSLA"
        )

        val foreignStockCurrentPrice = kisForeignStockCurrentPriceService.getForeignStockCurrentPrice(stockInfo)
        logger.info { "$foreignStockCurrentPrice" }
    }


}