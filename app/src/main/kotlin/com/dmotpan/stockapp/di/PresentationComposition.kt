package com.dmotpan.stockapp.di

import com.dmotpan.stockapp.stocks.StocksInteractor
import com.dmotpan.stockapp.stocks.StocksViewModel
import com.dmotpan.stockapp.stocks.common.viewModel
import com.dmotpan.stockapp.stocks.item.StockItemViewModel

class PresentationComposition(activityComposition: ActivityComposition) {
    private val stocksInteractor: StocksInteractor = StocksInteractor.create(
        activityComposition.stocksApi
    )
    val stocksViewModel by activityComposition.activity.viewModel { StocksViewModel(stocksInteractor) }
    val stocksItemViewModel get() = StockItemViewModel()
}
