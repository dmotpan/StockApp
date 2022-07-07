package com.dmotpan.stockapp.di

import com.dmotpan.stockapp.stocks.StocksActivity
import com.dmotpan.stockapp.stocks.item.StockItemLayout

class Injector(private val presentationComposition: PresentationComposition) {
    fun inject(activity: StocksActivity) {
        activity.stocksViewModel = presentationComposition.stocksViewModel
    }

    fun inject(view: StockItemLayout) {
        view.stocksItemViewModel = presentationComposition.stocksItemViewModel
    }
}
