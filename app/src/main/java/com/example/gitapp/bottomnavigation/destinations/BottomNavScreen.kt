package com.example.gitapp.bottomnavigation.destinations

import androidx.annotation.StringRes

sealed class BottomNavScreen(val route: String, @StringRes val resourceID: Int) {

    companion object {
        const val REPOS = "repos"
        const val FAVORITE = "fav"
        const val DEVS = "devs"
        const val SETTINGS = "settings"
    }
}