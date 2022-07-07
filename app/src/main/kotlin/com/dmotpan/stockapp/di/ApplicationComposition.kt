package com.dmotpan.stockapp.di

import android.app.Application
import com.dmotpan.stockapp.api.StocksApi

class ApplicationComposition(val application: Application) {
    val stocksApi: StocksApi by lazy {
        StocksApi.create()
    }
}
