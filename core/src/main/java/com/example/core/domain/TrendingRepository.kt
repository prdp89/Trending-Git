package com.example.core.domain

import com.example.core.dto.TrendingRepoDTO

interface TrendingRepository {

    suspend fun getTrendingRepos(page: Int): List<TrendingRepoDTO>
}