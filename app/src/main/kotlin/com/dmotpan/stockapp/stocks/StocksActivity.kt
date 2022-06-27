package com.dmotpan.stockapp.stocks

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmotpan.stockapp.api.StocksApi
import com.dmotpan.stockapp.databinding.ActivityStocksBinding
import com.dmotpan.stockapp.viewbinding.viewBinding

class StocksActivity : AppCompatActivity(), LifecycleOwner {
    private val binding by viewBinding {
        ActivityStocksBinding.inflate(LayoutInflater.from(this))
    }

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            StocksViewModelFactory(StocksInteractor.create(StocksApi.create()))
        )[StocksViewModel::class.java]
    }

    private val adapter = StocksAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.onSwipeToRefresh()
        }
        setUpRecycler()
        setUpObservers()
        viewModel.getStocks()
    }

    private fun setUpRecycler() {
        binding.recyclerStocksList.layoutManager = LinearLayoutManager(this)
        binding.recyclerStocksList.adapter = adapter
    }

    private fun setUpObservers() {
        viewModel.swipeToRefreshLiveData().observe(this) { isRefreshing ->
            binding.swipeToRefresh.isRefreshing = isRefreshing
        }

        viewModel.stocksSuccessLiveData().observe(this) { stocks ->
            adapter.submitList(stocks.asSuccess().data)
        }
        viewModel.stocksFailureLiveData().observe(this) { exception ->
            Log.e("Exception", exception.toString())
        }
        viewModel.stocksLoadingLiveData().observe(this) {
            binding.progressBar.visibility = VISIBLE
        }
        viewModel.loadingFinishedLiveData().observe(this) {
            binding.progressBar.visibility = GONE
        }
    }
}
