package com.example.gitapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.example.appscope.ApplicationScope
import com.example.settings.DefaultPreferences
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class GitApp : Application() {

    @Inject
    lateinit var defaultPreferences: DefaultPreferences

    @Inject
    @ApplicationScope
    lateinit var appScope: CoroutineScope

    override fun onCreate() {
        super.onCreate()
        applyDarkTheme()
    }

    private fun applyDarkTheme() {
        appScope.launch {
            defaultPreferences.darkTheme.firstOrNull()?.let { isDarkThemeEnabled ->
                if (isDarkThemeEnabled) enableNightMode() else disableNightMode()
            }
        }
    }

    private fun disableNightMode() {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
    }

    private fun enableNightMode() {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
    }
}