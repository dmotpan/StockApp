package com.dmotpan.stockapp.viewbinding

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import kotlin.LazyThreadSafetyMode.NONE

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
) = lazy(NONE) { bindingInflater.invoke(layoutInflater) }
