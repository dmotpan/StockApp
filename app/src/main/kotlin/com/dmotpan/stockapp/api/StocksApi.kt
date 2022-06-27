package com.dmotpan.stockapp.api

import kotlinx.coroutines.delay
import java.math.BigDecimal
import java.math.RoundingMode.HALF_EVEN
import kotlin.random.Random

interface StocksApi {
    companion object {
        fun create(): StocksApi = StocksApiImpl()
    }

    suspend fun getStocks(): List<StockDto>
}

private class StocksApiImpl : StocksApi {
    override suspend fun getStocks(): List<StockDto> {
        delay(1000)
        return listOf(
            StockDto(
                id = "1",
                name = "Apple Inc.",
                price = BigDecimal(
                    Random.nextDouble(106.06, 179.82)
                ).setScale(2, HALF_EVEN),
                currency = "$",
                priceChange = Random.nextDouble(-5.0, 5.0).toString()
            ),
            StockDto(
                id = "2",
                name = "Southwestern Energy Company",
                price = BigDecimal(
                    Random.nextDouble(6.5, 9.0)
                ).setScale(2, HALF_EVEN),
                currency = "$",
                priceChange = 0.toString()
            ),
            StockDto(
                id = "3",
                name = "Advanced Micro Devices, Inc",
                price = BigDecimal(
                    Random.nextDouble(75.06, 82.82)
                ).setScale(2, HALF_EVEN),
                currency = "$",
                priceChange = Random.nextDouble(-5.0, 5.0).toString()
            ),
            StockDto(
                id = "4",
                name = "Bank Of America Corporation",
                price = BigDecimal(
                    Random.nextDouble(20.06, 32.82)
                ).setScale(2, HALF_EVEN),
                currency = "$",
                priceChange = Random.nextDouble(-5.0, 5.0).toString()
            ),
            StockDto(
                id = "5",
                name = "Glinkgo Bioworks Holdings, Inc",
                price = BigDecimal(
                    Random.nextDouble(5.06, 12.82)
                ).setScale(2, HALF_EVEN),
                currency = "$",
                priceChange = Random.nextDouble(-5.0, 5.0).toString()
            ),
            StockDto(
                id = "6",
                name = "Zendesk",
                price = BigDecimal(
                    Random.nextDouble(75.06, 82.82)
                ).setScale(2, HALF_EVEN),
                currency = "$",
                priceChange = Random.nextDouble(-5.0, 5.0).toString()
            ),
            StockDto(
                id = "7",
                name = "Roblox Corporation",
                price = BigDecimal(
                    Random.nextDouble(122.06, 129.82)
                ).setScale(2, HALF_EVEN),
                currency = "$",
                priceChange = Random.nextDouble(-5.0, 5.0).toString()
            ),
            StockDto(
                id = "8",
                name = "Amazon.com, Inc",
                price = BigDecimal(
                    Random.nextDouble(112.06, 139.82)
                ).setScale(2, HALF_EVEN),
                currency = "$",
                priceChange = Random.nextDouble(-5.0, 5.0).toString()
            ),
            StockDto(
                id = "9",
                name = "NVIDIA Corporation",
                price = BigDecimal(
                    Random.nextDouble(82.06, 92.82)
                ).setScale(2, HALF_EVEN),
                currency = "$",
                priceChange = Random.nextDouble(-5.0, 5.0).toString()
            ),
            StockDto(
                id = "10",
                name = "Meta Platforms, Inc",
                price = BigDecimal(
                    Random.nextDouble(112.06, 149.82)
                ).setScale(2, HALF_EVEN),
                currency = "$",
                priceChange = Random.nextDouble(-5.0, 5.0).toString()
            )
        )
    }
}
