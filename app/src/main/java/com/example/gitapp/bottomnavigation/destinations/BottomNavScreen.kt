package com.example.gitapp.bottomnavigation.destinations

import androidx.annotation.StringRes

sealed class BottomNavScreen(val route: String, @StringRes val resourceID: Int) {

    companion object {
        const val SEARCH = "search"
        const val REPOS = "repos"
        const val DEVS = "devs"
    }
}