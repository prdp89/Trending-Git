package com.example.reposui.di

import com.example.reposinteractors.GetTrendingRepoData
//import com.example.reposinteractors.TrendingRepoInteractors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoListModule {

    /*@Provides
    fun provideTrendingRepoData( trendingRepoInteractors: TrendingRepoInteractors
    ): GetTrendingRepoData {
        return trendingRepoInteractors.getTrendingRepo
    }*/
}