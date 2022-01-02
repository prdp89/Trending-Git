package com.example.toaster

import android.content.Context
import com.example.toaster.core.Toaster
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ToasterModule {

    @Provides
    @Singleton
    fun toaster(@ApplicationContext context: Context) = Toaster(context)
}