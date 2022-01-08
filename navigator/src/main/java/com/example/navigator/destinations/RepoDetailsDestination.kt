package com.example.navigator.destinations

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument
import com.example.navigator.NavigationDestination

object RepoDetailsDestination : NavigationDestination {
    override fun route(): String = REPO_DETAILS_BOTTOM_NAV_ROUTE

    override val arguments: List<NamedNavArgument>
        get() = listOf(navArgument(REPO_ID_PARAM) { type = NavType.IntType })

    fun createRepoDetailsRoute(repoID: Int) = "$REPO_DETAILS_ROUTE/${repoID}"

    const val REPO_ID_PARAM = "repo"
    private const val REPO_DETAILS_ROUTE = "repo_details"
    private const val REPO_DETAILS_BOTTOM_NAV_ROUTE = "$REPO_DETAILS_ROUTE/{$REPO_ID_PARAM}"
}