package com.sample.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import com.sample.bottomnavigation.destinations.DevsBottomNavRoute
import com.sample.bottomnavigation.destinations.RepoBottomNavRoute
import com.sample.bottomnavigation.destinations.SettingsBottomNavRoute

object BottomNav {

    val bottomNavigationEntries =
            listOf(
                    BottomEntry(
                            RepoBottomNavRoute,
                            Icons.Filled.Home
                    ),
                    BottomEntry(
                            DevsBottomNavRoute,
                            Icons.Filled.Create
                    ),
                    BottomEntry(
                            SettingsBottomNavRoute,
                            Icons.Filled.Settings
                    ),
            )

    val hideBottomNavOnDestinations = listOf(
            RepoBottomNavRoute.route,
            DevsBottomNavRoute.route,
            SettingsBottomNavRoute.route
    )
}