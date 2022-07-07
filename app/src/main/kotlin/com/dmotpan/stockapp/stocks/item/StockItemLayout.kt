package com.dmotpan.stockapp.stocks.item

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.findViewTreeLifecycleOwner
import com.dmotpan.stockapp.R
import com.dmotpan.stockapp.api.Stock
import com.dmotpan.stockapp.databinding.ListItemStockBinding
import com.dmotpan.stockapp.stocks.common.BaseActivity

class StockItemLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding = ListItemStockBinding.inflate(LayoutInflater.from(context), this, true)
    lateinit var stocksItemViewModel: StockItemViewModel

    override fun onAttachedToWindow() {
        (context as BaseActivity).viewInjector.inject(this)
        super.onAttachedToWindow()
        setUpObservers()
    }

    fun bind(stock: Stock) {
        stocksItemViewModel.onBind(stock)
    }

    private fun setUpObservers() {
        stocksItemViewModel.stockNameLiveData().observe(findViewTreeLifecycleOwner()!!) { name ->
            binding.stockName.text = name
        }
        stocksItemViewModel.stockPriceLiveData().observe(findViewTreeLifecycleOwner()!!) { price ->
            binding.stockPrice.text = price
        }
        stocksItemViewModel.stockPriceLiveDecreaseData().observe(findViewTreeLifecycleOwner()!!) {
            binding.stockPrice.setTextColor(ContextCompat.getColor(context, R.color.red))
        }
        stocksItemViewModel.stockPriceIncreaseLiveData().observe(findViewTreeLifecycleOwner()!!) {
            binding.stockPrice.setTextColor(ContextCompat.getColor(context, R.color.green))
        }
        stocksItemViewModel.stockPriceLiveNeutralData().observe(findViewTreeLifecycleOwner()!!) {
            binding.stockPrice.setTextColor(ContextCompat.getColor(context, R.color.black))
        }
    }
}
