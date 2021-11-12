package com.example.settings

import kotlinx.coroutines.flow.Flow

interface DefaultPrefsContract {
    val darkTheme: Flow<Boolean>
    suspend fun changeTheme(isDarkThemeEnabled: Boolean)

    companion object{
        const val DARK_THEME_KEY = "dark_theme_git"
    }
}