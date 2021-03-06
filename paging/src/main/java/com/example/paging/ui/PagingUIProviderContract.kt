package com.example.paging.ui

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.paging.NoConnectionException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi

interface PagingUIProviderContract {

    fun <T : Any> isDataEmptyWithError(
            pagingItems: LazyPagingItems<T>
    ): Boolean

    fun <T : Any> isDataEmpty(pagingItems: LazyPagingItems<T>): Boolean


    fun <T : Any> progressBarVisibility(
            pagingItems: LazyPagingItems<T>
    ): Boolean

    fun <T : Any> isSwipeToRefreshEnabled(pagingItems: LazyPagingItems<T>): Boolean
    fun onPaginationReachedError(append: LoadState, @StringRes errorMessage: Int)

    @ExperimentalCoroutinesApi
    @Composable
    fun <T : Any> OnError(
            scope: CoroutineScope,
            pagingItems: LazyPagingItems<T>,
            noInternetUI: @Composable () -> Unit,
            errorUI: @Composable () -> Unit
    )

    fun isLoadStateNoConnectionException(state: LoadState): Boolean =
            state is LoadState.Error && state.error is NoConnectionException


    fun isLoadStateError(state: LoadState): Boolean =
            state is LoadState.Error
}