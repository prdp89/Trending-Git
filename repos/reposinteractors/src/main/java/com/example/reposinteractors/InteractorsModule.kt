package com.example.reposinteractors

import com.example.core.api.TrendingRepoApi
import com.example.core.domain.TrendingRepository
import com.example.reposinteractors.remote.TrendingRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {

    @Provides
    @Singleton
    fun provideCoinRepository(api: TrendingRepoApi): TrendingRepository {
        return TrendingRepositoryImpl(api)
    }
}