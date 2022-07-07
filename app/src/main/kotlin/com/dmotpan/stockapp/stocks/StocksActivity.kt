package com.dmotpan.stockapp.stocks

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmotpan.stockapp.databinding.ActivityStocksBinding
import com.dmotpan.stockapp.stocks.common.BaseActivity
import com.dmotpan.stockapp.viewbinding.viewBinding

class StocksActivity : BaseActivity(), LifecycleOwner {
    private val binding by viewBinding {
        ActivityStocksBinding.inflate(LayoutInflater.from(this))
    }

    lateinit var stocksViewModel: StocksViewModel

    private val adapter = StocksAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.swipeToRefresh.setOnRefreshListener {
            stocksViewModel.onSwipeToRefresh()
        }
        setUpRecycler()
        setUpObservers()
        stocksViewModel.getStocks()
    }

    private fun setUpRecycler() {
        binding.recyclerStocksList.layoutManager = LinearLayoutManager(this)
        binding.recyclerStocksList.adapter = adapter
    }

    private fun setUpObservers() {
        stocksViewModel.swipeToRefreshLiveData().observe(this) { isRefreshing ->
            binding.swipeToRefresh.isRefreshing = isRefreshing
        }

        stocksViewModel.stocksSuccessLiveData().observe(this) { stocks ->
            adapter.submitList(stocks.asSuccess().data)
        }
        stocksViewModel.stocksFailureLiveData().observe(this) { exception ->
            Log.e("Exception", exception.toString())
        }
        stocksViewModel.stocksLoadingLiveData().observe(this) {
            binding.progressBar.visibility = VISIBLE
        }
        stocksViewModel.loadingFinishedLiveData().observe(this) {
            binding.progressBar.visibility = GONE
        }
    }
}
