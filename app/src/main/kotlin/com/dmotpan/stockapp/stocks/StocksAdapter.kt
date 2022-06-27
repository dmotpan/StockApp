package com.dmotpan.stockapp.stocks

import android.view.ViewGroup
import androidx.core.view.doOnAttach
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dmotpan.stockapp.api.Stock
import com.dmotpan.stockapp.stocks.item.StockItemLayout

internal object DiffCallback : DiffUtil.ItemCallback<Stock>() {
    override fun areItemsTheSame(first: Stock, second: Stock): Boolean = first.name == second.name

    override fun areContentsTheSame(first: Stock, second: Stock): Boolean = first == second
}

class StocksAdapter : ListAdapter<Stock, StocksViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StocksViewHolder {
        return StocksViewHolder(StockItemLayout(parent.context))
    }

    override fun onBindViewHolder(holder: StocksViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class StocksViewHolder(private val view: StockItemLayout) : ViewHolder(view) {
    fun bind(stock: Stock) {
        view.doOnAttach { view.bind(stock) }
    }
}
