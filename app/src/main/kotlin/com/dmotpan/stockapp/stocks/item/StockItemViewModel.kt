package com.dmotpan.stockapp.stocks.item

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dmotpan.stockapp.api.PriceChange
import com.dmotpan.stockapp.api.PriceChange.DECREASE
import com.dmotpan.stockapp.api.PriceChange.INCREASE
import com.dmotpan.stockapp.api.PriceChange.NEUTRAL
import com.dmotpan.stockapp.api.Stock

class StockItemViewModel : ViewModel() {
    private val stockNameLiveData = MutableLiveData<String>()
    private val stockPriceLiveData = MutableLiveData<String>()
    private val stockPriceIncreaseLiveData = MutableLiveData<PriceChange>()
    private val stockPriceDecreaseLiveData = MutableLiveData<PriceChange>()
    private val stockPriceNeutralLiveData = MutableLiveData<PriceChange>()

    fun stockNameLiveData(): LiveData<String> = stockNameLiveData
    fun stockPriceLiveData(): LiveData<String> = stockPriceLiveData
    fun stockPriceIncreaseLiveData(): LiveData<PriceChange> = stockPriceIncreaseLiveData
    fun stockPriceLiveDecreaseData(): LiveData<PriceChange> = stockPriceDecreaseLiveData
    fun stockPriceLiveNeutralData(): LiveData<PriceChange> = stockPriceNeutralLiveData

    fun onBind(stock: Stock) {
        stockNameLiveData.value = stock.name
        stockPriceLiveData.value = stock.price + stock.currency
        when (stock.priceChange) {
            INCREASE -> stockPriceIncreaseLiveData.value = INCREASE
            DECREASE -> stockPriceDecreaseLiveData.value = DECREASE
            NEUTRAL -> stockPriceNeutralLiveData.value = NEUTRAL
        }
    }
}
