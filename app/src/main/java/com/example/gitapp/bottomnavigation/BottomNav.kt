package com.example.gitapp.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import com.example.gitapp.bottomnavigation.destinations.FavoritesBottomNavRoute
import com.example.gitapp.bottomnavigation.destinations.SearchBottomNavRoute
import com.example.gitapp.bottomnavigation.destinations.SettingsBottomNavRoute

object BottomNav {

    val bottomNavigationEntries =
            listOf(
                    BottomEntry(
                            SearchBottomNavRoute,
                            Icons.Filled.Search
                    ),
                    BottomEntry(
                            FavoritesBottomNavRoute,
                            Icons.Filled.Favorite
                    ),
                    BottomEntry(
                            SettingsBottomNavRoute,
                            Icons.Filled.Settings
                    ),
            )
}