package com.example.core.domain

import com.example.trending.repo.TrendingRepo

interface TrendingRepository {

    //return type must be a DTO
    suspend fun getTrendingRepos(page: Int): List<TrendingRepo>
}