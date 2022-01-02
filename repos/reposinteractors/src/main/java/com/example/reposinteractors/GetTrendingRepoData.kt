package com.example.reposinteractors

import com.example.core.domain.DataState
import com.example.trending.repo.TrendingRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

public class GetTrendingRepoData @Inject constructor (
    private val latestBooksDataSourceFactory: TrendingRepoDataSource.TrendingRepoDataSourceFactory) {

    fun execute() : Flow<DataState<List<TrendingRepo>>> = flow {
        //val key = ioDispatcher.key
        emit(DataState.Success<List<TrendingRepo>>(listOf()))
    }
}