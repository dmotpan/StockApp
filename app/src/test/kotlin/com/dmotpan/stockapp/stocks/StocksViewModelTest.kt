package com.dmotpan.stockapp.stocks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dmotpan.stockapp.MainCoroutineRule
import com.dmotpan.stockapp.api.Resource.Failure
import com.dmotpan.stockapp.api.Resource.Loading
import com.dmotpan.stockapp.api.Resource.Success
import com.dmotpan.stockapp.api.Stock
import com.dmotpan.stockapp.captureValues
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.io.IOException

@OptIn(DelicateCoroutinesApi::class)
class StocksViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val interactor = mock(StocksInteractor::class.java)
    private var viewModel = StocksViewModel(interactor)

    @Test
    fun `shows and hides swipe to refresh`() = runTest {
        viewModel.swipeToRefreshLiveData().captureValues {
            viewModel.onSwipeToRefresh()
            assertSendsValues(true, false)
        }
    }

    @Test
    fun `dispatches stocks success value`() = runTest(UnconfinedTestDispatcher()) {
        `when`(interactor.getStocks()).thenReturn(listOf())

        viewModel.getStocks()
        advanceUntilIdle()

        assertEquals(Loading(null), viewModel.stocksLoadingLiveData().value)
        assertEquals(Success(listOf<Stock>()), viewModel.stocksSuccessLiveData().value)
        assertEquals(true, viewModel.loadingFinishedLiveData().value)
    }

    @Test
    fun `dispatches stocks receive failure`() = runTest {
        val exception = IOException()
        `when`(interactor.getStocks()).then { throw exception }

        viewModel.getStocks()
        advanceUntilIdle()

        assertEquals(Loading(null), viewModel.stocksLoadingLiveData().value)
        assertEquals(Failure<IOException>(exception), viewModel.stocksFailureLiveData().value)
        assertEquals(true, viewModel.loadingFinishedLiveData().value)
    }
}
