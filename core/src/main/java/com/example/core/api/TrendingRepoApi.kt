package com.example.core.api

import com.example.core.dto.RepoContributorsDTO
import com.example.core.dto.TrendingRepoDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TrendingRepoApi {

    @GET("orgs/{org}/repos")
    suspend fun getTrendingRepositories(@Path("org") org : String, @Query("page") page : Int): List<TrendingRepoDTO>

    @GET("repos/{owner}/{repo}")
    suspend fun getRepoDetails(@Path("owner") owner: String, @Path("repo") repoName: String) : TrendingRepoDTO

    @GET("repos/{owner}/{repo}/contributors")
    suspend fun getRepoContributors(@Path("owner") owner: String, @Path("repo") repoName: String) : List<RepoContributorsDTO>

}