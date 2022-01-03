package com.example.core.api

import com.example.core.dto.TrendingRepoDTO
import com.example.trending.repo.TrendingRepo
import retrofit2.http.GET
import retrofit2.http.Query

interface TrendingRepoApi {

    //update return type to DTO
    @GET("repos")
    suspend fun getTrendingRepositories(@Query("page") page : Int): List<TrendingRepoDTO>
}