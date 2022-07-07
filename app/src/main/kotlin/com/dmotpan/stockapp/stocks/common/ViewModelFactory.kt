package com.dmotpan.stockapp.stocks.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified T : ViewModel> Fragment.fragmentViewModel(
    noinline provider: (() -> T)? = null
) = FragmentViewModelDelegate(T::class.java, this, provider)

inline fun <reified T : ViewModel> FragmentActivity.viewModel(
    noinline provider: (() -> T)? = null
) = ActivityVewModelDelegate(T::class.java, this, provider)

internal class ViewModelFactory<T>(
    private val provider: (() -> T)
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return provider.invoke() as T
    }
}

class ActivityVewModelDelegate<T : ViewModel>(
    private val valueClass: Class<T>,
    private val activity: FragmentActivity,
    private val provider: (() -> T)?,
) : ReadOnlyProperty<Any, T> {
    private var value: T? = null
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        if (value == null) {
            val factory = provider?.let { ViewModelFactory(it) }
            value = ViewModelProvider(
                activity,
                factory ?: activity.defaultViewModelProviderFactory
            )[valueClass]
        }
        return value ?: throw IllegalStateException("Property ${property.name} could not be read")
    }
}

class FragmentViewModelDelegate<T : ViewModel>(
    private val valueClass: Class<T>,
    private val fragment: Fragment,
    private val provider: (() -> T)?,
) : ReadOnlyProperty<Any, T> {
    private var value: T? = null

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        if (value == null) {
            val factory = provider?.let { ViewModelFactory(it) }
            value = ViewModelProvider(
                fragment,
                factory ?: fragment.defaultViewModelProviderFactory
            )[valueClass]
        }
        return value ?: throw IllegalStateException("Property ${property.name} could not be read")
    }
}
