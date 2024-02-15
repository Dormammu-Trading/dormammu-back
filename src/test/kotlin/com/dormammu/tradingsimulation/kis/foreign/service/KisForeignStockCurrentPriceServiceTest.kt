package com.dormammu.tradingsimulation.kis.foreign.service

import com.dormammu.tradingsimulation.kis.foreign.domain.StockInfo
import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.DisplayName
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.test.context.ContextConfiguration

private val logger = KotlinLogging.logger {}


@ContextConfiguration()
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayName("해외주식 현재가 API TEST")
class KisForeignStockCurrentPriceServiceTest(
    kisForeignStockCurrentPriceService: KisForeignStockCurrentPriceService
) : BehaviorSpec({

    given("정상 입력") {
        val stockInfo = StockInfo(
            "NAS",
            "TSLA"
        )
        When("해외 주식 현재가 Kis API 호출") {
            val foreignStockCurrentPrice = kisForeignStockCurrentPriceService.getForeignStockCurrentPrice(stockInfo)
            then("정상 응답한다"){
                logger.info { "$foreignStockCurrentPrice" }
                foreignStockCurrentPrice!!.rtCd shouldBe "0"
                foreignStockCurrentPrice.output!!.rsym shouldNotBe ""
            }
        }
    }

    given("필수값 누락된 입력") {
        val stockInfo = StockInfo(
            "",
            ""
        )
        When("해외 주식 현재가 Kis API 호출") {
            val foreignStockCurrentPrice = kisForeignStockCurrentPriceService.getForeignStockCurrentPrice(stockInfo)
            then("정상 응답한다"){
                logger.info { "$foreignStockCurrentPrice" }
                foreignStockCurrentPrice!!.rtCd shouldBe "0"
                foreignStockCurrentPrice.output!!.rsym shouldBe ""
            }
        }
    }
}) {

}