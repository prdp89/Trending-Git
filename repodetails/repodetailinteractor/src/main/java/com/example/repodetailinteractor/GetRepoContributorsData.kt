package com.example.repodetailinteractor

import com.example.core.domain.DataState
import com.example.core.domain.TrendingRepository
import com.example.core.dto.toRepoContributors
import com.example.core.dto.toTrendingRepo
import com.example.trending.repo.RepoContributors
import com.example.trending.repo.TrendingRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetRepoContributorsData @Inject constructor(
    private val trendingRepository : TrendingRepository
    ) {

    operator fun invoke(repoName: String): Flow<DataState<List<RepoContributors>>> = flow {
        try {
            emit(DataState.Loading())
            val repo = trendingRepository.getRepoContributors("google", repoName).map { it.toRepoContributors() }
            emit(DataState.Success(repo))
        } catch(e: Exception) {
            emit(DataState.Failure(e.localizedMessage?: "Couldn't reach server. Check your internet connection."))
        }
    }
}