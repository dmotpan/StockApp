package com.dmotpan.stockapp.api

import com.dmotpan.stockapp.api.PriceChange.DECREASE
import com.dmotpan.stockapp.api.PriceChange.INCREASE
import com.dmotpan.stockapp.api.PriceChange.NEUTRAL
import java.math.BigDecimal

data class StockDto(
    val id: String,
    val name: String,
    val price: BigDecimal,
    val currency: String,
    val priceChange: String
)

data class Stock(
    val name: String,
    val price: String,
    val currency: String,
    val priceChange: PriceChange
)

enum class PriceChange {
    INCREASE,
    DECREASE,
    NEUTRAL
}

fun StockDto.toStock(): Stock {
    return Stock(
        name = name,
        price = price.toString(),
        currency = currency,
        priceChange = priceChange.toPriceChange()
    )
}

private fun String.toPriceChange(): PriceChange {
    return when {
        this.toDouble() > 0 -> INCREASE
        this.toDouble() < 0 -> DECREASE
        else -> NEUTRAL
    }
}
