package com.dmotpan.stockapp

import android.app.Application
import com.dmotpan.stockapp.di.ApplicationComposition

class StocksApplication : Application() {
    lateinit var appComposition: ApplicationComposition

    override fun onCreate() {
        appComposition = ApplicationComposition(this)
        super.onCreate()
    }
}
