package com.example.gitapp.di

//import com.example.reposinteractors.TrendingRepoInteractors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TrendingRepoInteractorsModule {

   /* @Provides
    @Singleton
    fun provideTrendingRepoInteractors(): TrendingRepoInteractors{
        return TrendingRepoInteractors.build()
    }*/
}