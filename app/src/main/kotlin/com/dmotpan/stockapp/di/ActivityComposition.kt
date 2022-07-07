package com.dmotpan.stockapp.di

import androidx.appcompat.app.AppCompatActivity
import com.dmotpan.stockapp.api.StocksApi

class ActivityComposition(
    val activity: AppCompatActivity,
    private val appComposition: ApplicationComposition
) {
    val stocksApi: StocksApi get() = appComposition.stocksApi
}
