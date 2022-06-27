package com.dmotpan.stockapp.stocks

import com.dmotpan.stockapp.api.Stock
import com.dmotpan.stockapp.api.StocksApi
import com.dmotpan.stockapp.api.toStock

interface StocksInteractor {
    companion object {
        fun create(api: StocksApi): StocksInteractor = StocksInteractorImpl(api)
    }

    suspend fun getStocks(): List<Stock>
}

private class StocksInteractorImpl(private val api: StocksApi) : StocksInteractor {
    override suspend fun getStocks(): List<Stock> {
        return api.getStocks()
            .sortedBy { it.price }
            .takeLast(5)
            .map { it.toStock() }
            .reversed()
    }
}
