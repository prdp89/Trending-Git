package com.sample.gitapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GitApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}