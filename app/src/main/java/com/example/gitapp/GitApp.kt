package com.example.gitapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GitApp : Application() {

    override fun onCreate() {
        super.onCreate()
        applyDarkTheme()
    }

    private fun applyDarkTheme() {

    }

    private fun disableNightMode() {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
    }

    private fun enableNightMode() {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)

    }
}