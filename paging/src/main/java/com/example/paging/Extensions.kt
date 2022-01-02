package com.example.paging

import androidx.paging.PagingSource
import androidx.paging.compose.LazyPagingItems

val LazyPagingItems<*>.appendState get() = loadState.append
val LazyPagingItems<*>.refreshState get() = loadState.refresh
val LazyPagingItems<*>.prependState get() = loadState.prepend

fun <T : Any> canNotLoadMoreContent(): PagingSource.LoadResult.Page<Int, T> =
        PagingSource.LoadResult.Page(emptyList(), null, null)
