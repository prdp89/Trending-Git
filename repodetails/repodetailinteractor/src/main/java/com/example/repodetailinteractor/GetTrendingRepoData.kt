package com.example.repodetailinteractor

import com.example.core.domain.DataState
import com.example.core.domain.TrendingRepository
import com.example.core.dto.toTrendingRepo
import com.example.trending.repo.TrendingRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetTrendingRepoData @Inject constructor(
    private val trendingRepository : TrendingRepository
    ) {

    operator fun invoke(repoName: String, author: String): Flow<DataState<TrendingRepo>> = flow {
        try {
            emit(DataState.Loading<TrendingRepo>())
            val repo = trendingRepository.getTrendingRepo(author, repoName).toTrendingRepo()
            emit(DataState.Success<TrendingRepo>(repo))
        } catch(e: Exception) {
            emit(DataState.Failure<TrendingRepo>(e.localizedMessage?: "Couldn't reach server. Check your internet connection."))
        }
    }
}