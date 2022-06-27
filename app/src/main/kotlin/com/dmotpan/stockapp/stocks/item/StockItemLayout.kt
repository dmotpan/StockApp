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

class StockItemLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding = ListItemStockBinding.inflate(LayoutInflater.from(context), this, true)
    private val viewModel = StockItemViewModel()

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setUpObservers()
    }

    fun bind(stock: Stock) {
        viewModel.onBind(stock)
    }

    private fun setUpObservers() {
        viewModel.stockNameLiveData().observe(findViewTreeLifecycleOwner()!!) { name ->
            binding.stockName.text = name
        }
        viewModel.stockPriceLiveData().observe(findViewTreeLifecycleOwner()!!) { price ->
            binding.stockPrice.text = price
        }
        viewModel.stockPriceLiveDecreaseData().observe(findViewTreeLifecycleOwner()!!) {
            binding.stockPrice.setTextColor(ContextCompat.getColor(context, R.color.red))
        }
        viewModel.stockPriceIncreaseLiveData().observe(findViewTreeLifecycleOwner()!!) {
            binding.stockPrice.setTextColor(ContextCompat.getColor(context, R.color.green))
        }
        viewModel.stockPriceLiveNeutralData().observe(findViewTreeLifecycleOwner()!!) {
            binding.stockPrice.setTextColor(ContextCompat.getColor(context, R.color.black))
        }
    }
}
