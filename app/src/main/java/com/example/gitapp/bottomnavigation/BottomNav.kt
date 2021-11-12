package com.example.gitapp.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import com.example.gitapp.bottomnavigation.destinations.RepoBottomNavRoute
import com.example.gitapp.bottomnavigation.destinations.SearchBottomNavRoute
import com.example.gitapp.bottomnavigation.destinations.DevBottomNavRoute

object BottomNav {

    val bottomNavigationEntries =
            listOf(
                    BottomEntry(
                            RepoBottomNavRoute,
                            Icons.Filled.Bookmark
                    ),
                    BottomEntry(
                            SearchBottomNavRoute,
                            Icons.Filled.Search
                    ),
                    BottomEntry(
                            DevBottomNavRoute,
                            Icons.Filled.PersonPin
                    ),
            )
}