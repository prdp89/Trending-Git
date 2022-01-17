package com.example.core.domain

import com.example.core.dto.RepoContributorsDTO
import com.example.core.dto.TrendingRepoDTO

interface TrendingRepository {

    suspend fun getTrendingRepos(page: Int, repoName: String): List<TrendingRepoDTO>
    suspend fun getTrendingRepo(owner: String, repoName: String): TrendingRepoDTO
    suspend fun getRepoContributors(owner: String, repoName: String): List<RepoContributorsDTO>
}