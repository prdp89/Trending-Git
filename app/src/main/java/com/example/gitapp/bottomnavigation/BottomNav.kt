package com.example.gitapp.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import com.example.gitapp.bottomnavigation.destinations.RepoBottomNavRoute
import com.example.gitapp.bottomnavigation.destinations.FavoriteBottomNavRoute
import com.example.gitapp.bottomnavigation.destinations.DevBottomNavRoute
import com.example.gitapp.bottomnavigation.destinations.SettingsBottomNavRoute

object BottomNav {

    val bottomNavigationEntries =
            listOf(
                    BottomEntry(
                            RepoBottomNavRoute,
                            Icons.Filled.Bookmark
                    ),
                    BottomEntry(
                            FavoriteBottomNavRoute,
                            Icons.Filled.Favorite
                    ),
                    BottomEntry(
                            DevBottomNavRoute,
                            Icons.Filled.PersonPin
                    ),
                    BottomEntry(
                            SettingsBottomNavRoute,
                            Icons.Filled.Settings
                    ),
            )
}