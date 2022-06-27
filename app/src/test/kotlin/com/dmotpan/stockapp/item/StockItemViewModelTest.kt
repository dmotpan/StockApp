package com.dmotpan.stockapp.item

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dmotpan.stockapp.api.PriceChange.DECREASE
import com.dmotpan.stockapp.api.PriceChange.INCREASE
import com.dmotpan.stockapp.api.PriceChange.NEUTRAL
import com.dmotpan.stockapp.api.Stock
import com.dmotpan.stockapp.stocks.item.StockItemViewModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class StockItemViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val viewModel = StockItemViewModel()
    private val stockItem = Stock("Apple", "125", "$", INCREASE)

    @Test
    fun `sets name when binding item`() {
        viewModel.onBind(stockItem)

        assertEquals("Apple", viewModel.stockNameLiveData().value)
    }

    @Test
    fun `sets price with currency when binding item`() {
        viewModel.onBind(stockItem)

        assertEquals("125$", viewModel.stockPriceLiveData().value)
    }

    @Test
    fun `updates increased price livedata when price increased`() {
        viewModel.onBind(stockItem)

        assertEquals(INCREASE, viewModel.stockPriceIncreaseLiveData().value)
    }

    @Test
    fun `updates decreased price livedata when price decreased`() {
        val stockItem = Stock("Apple", "125", "$", DECREASE)

        viewModel.onBind(stockItem)

        assertEquals(DECREASE, viewModel.stockPriceLiveDecreaseData().value)
    }

    @Test
    fun `updates neutral price livedata when price remained neutral`() {
        val stockItem = Stock("Apple", "125", "$", NEUTRAL)

        viewModel.onBind(stockItem)

        assertEquals(NEUTRAL, viewModel.stockPriceLiveNeutralData().value)
    }
}
