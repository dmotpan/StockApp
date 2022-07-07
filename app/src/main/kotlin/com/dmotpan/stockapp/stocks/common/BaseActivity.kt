package com.dmotpan.stockapp.stocks.common

import androidx.appcompat.app.AppCompatActivity
import com.dmotpan.stockapp.StocksApplication
import com.dmotpan.stockapp.di.ActivityComposition
import com.dmotpan.stockapp.di.Injector
import com.dmotpan.stockapp.di.PresentationComposition

open class BaseActivity : AppCompatActivity() {
    private val appCompositionRoot get() = (application as StocksApplication).appComposition

    private val activityComposition by lazy {
        ActivityComposition(this, appCompositionRoot)
    }

    private val presentationComposition by lazy {
        PresentationComposition(activityComposition)
    }
    protected val injector get() = Injector(presentationComposition)
    val viewInjector get() = injector
}
