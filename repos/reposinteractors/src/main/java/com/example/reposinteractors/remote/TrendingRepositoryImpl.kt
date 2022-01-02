package com.example.reposinteractors.remote

import com.example.core.domain.TrendingRepository
import com.example.core.api.TrendingRepoApi
import com.example.trending.repo.TrendingRepo
import javax.inject.Inject

class TrendingRepositoryImpl @Inject constructor(private val trendingRepoApi: TrendingRepoApi) : TrendingRepository {
    override suspend fun getTrendingRepos(page: Int): List<TrendingRepo> {
        return trendingRepoApi.getTrendingRepositories(page)
    }
}