package com.example.reposinteractors.remote

import com.example.core.domain.TrendingRepository
import com.example.core.api.TrendingRepoApi
import com.example.core.dto.RepoContributorsDTO
import com.example.core.dto.TrendingRepoDTO
import com.example.trending.repo.TrendingRepo
import javax.inject.Inject

class TrendingRepositoryImpl @Inject constructor(private val trendingRepoApi: TrendingRepoApi) : TrendingRepository {
    override suspend fun getTrendingRepos(page: Int): List<TrendingRepoDTO> {
        //todo:pass google in search..
        return trendingRepoApi.getTrendingRepositories("google", page)
    }

    override suspend fun getTrendingRepo(owner: String, repoName: String): TrendingRepoDTO {
        return trendingRepoApi.getRepoDetails(owner, repoName);
    }

    override suspend fun getRepoContributors(owner: String, repoName: String): List<RepoContributorsDTO> {
        return trendingRepoApi.getRepoContributors(owner, repoName);
    }
}