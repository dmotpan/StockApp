package com.dmotpan.stockapp.stocks

import com.dmotpan.stockapp.api.PriceChange.DECREASE
import com.dmotpan.stockapp.api.PriceChange.INCREASE
import com.dmotpan.stockapp.api.PriceChange.NEUTRAL
import com.dmotpan.stockapp.api.Stock
import com.dmotpan.stockapp.api.StockDto
import com.dmotpan.stockapp.api.StocksApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

@OptIn(ExperimentalCoroutinesApi::class)
class StocksInteractorTest {
    private val interactor = StocksInteractor.create(FakeStocksApi())

    @Test
    fun `returns top 5 stocks with the highest prices`() = runTest {
        assertEquals(
            interactor.getStocks(),
            listOf(
                Stock("Alphabet", "6", "$", INCREASE),
                Stock("Bank", "5", "$", DECREASE),
                Stock("Amazon", "4", "$", NEUTRAL),
                Stock("NVIDIA", "3", "$", DECREASE),
                Stock("Mc", "2", "$", DECREASE),
            )
        )
    }
}

class FakeStocksApi : StocksApi {
    override suspend fun getStocks(): List<StockDto> {
        return listOf(
            StockDto(id = "1", "Apple", BigDecimal(1), "$", "-1.0"),
            StockDto(id = "1", "Mc", BigDecimal(2), "$", "-1.0"),
            StockDto(id = "1", "NVIDIA", BigDecimal(3), "$", "-1.0"),
            StockDto(id = "1", "Amazon", BigDecimal(4), "$", "0.0"),
            StockDto(id = "1", "Bank", BigDecimal(5), "$", "-1.0"),
            StockDto(id = "1", "Alphabet", BigDecimal(6), "$", "1.0"),
        )
    }
}
