package com.sample.bottomnavigation.destinations

sealed class BottomNavScreen(val route: String, val pageName: String) {

    companion object {
        const val REPOS = "repos"
        const val DEVS = "devs"
        const val SETTINGS = "settings"
    }
}