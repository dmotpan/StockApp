package com.dmotpan.stockapp.stocks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dmotpan.stockapp.api.Resource
import com.dmotpan.stockapp.api.Resource.Failure
import com.dmotpan.stockapp.api.Resource.Loading
import com.dmotpan.stockapp.api.Resource.Success
import com.dmotpan.stockapp.api.Stock
import kotlinx.coroutines.launch
import java.io.IOException

class StocksViewModel(
    private val interactor: StocksInteractor
) : ViewModel() {
    private val stocksSuccessLiveData = MutableLiveData<Resource<List<Stock>>>()
    private val stocksFailureLiveData = MutableLiveData<Resource<Exception>>()
    private val stocksLoadingLiveData = MutableLiveData<Resource<List<Stock>>>()
    private val swipeToRefreshLiveData = MutableLiveData<Boolean>()
    private val loadingFinishedLiveData = MutableLiveData<Boolean>()

    fun stocksSuccessLiveData(): LiveData<Resource<List<Stock>>> = stocksSuccessLiveData
    fun stocksFailureLiveData(): LiveData<Resource<Exception>> = stocksFailureLiveData
    fun stocksLoadingLiveData(): LiveData<Resource<List<Stock>>> = stocksLoadingLiveData
    fun swipeToRefreshLiveData(): LiveData<Boolean> = swipeToRefreshLiveData
    fun loadingFinishedLiveData(): LiveData<Boolean> = loadingFinishedLiveData

    fun getStocks() {
        viewModelScope.launch {
            try {
                stocksLoadingLiveData.value = Loading()
                val stocks = interactor.getStocks()
                stocksSuccessLiveData.value = Success(stocks)
            } catch (exception: IOException) {
                stocksFailureLiveData.value = Failure(exception)
            } finally {
                loadingFinishedLiveData.value = true
            }
        }
    }

    fun onSwipeToRefresh() {
        swipeToRefreshLiveData.value = true
        getStocks()
        swipeToRefreshLiveData.value = false
    }
}

internal class StocksViewModelFactory(
    private val stocksInteractor: StocksInteractor
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StocksViewModel(stocksInteractor) as T
    }
}
