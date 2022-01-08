package com.example.navigator.destinations

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument
import com.example.navigator.NavigationDestination

object RepoDetailsDestination : NavigationDestination {
    override fun route(): String = REPO_DETAILS_BOTTOM_NAV_ROUTE

    override val arguments: List<NamedNavArgument>
        get() = listOf(navArgument(REPO_NAME_PARAM) { type = NavType.StringType })

    fun createRepoDetailsRoute(repoName: String) = "$REPO_DETAILS_ROUTE/${repoName}"

    const val REPO_NAME_PARAM = "repo"
    private const val REPO_DETAILS_ROUTE = "repo_details"
    private const val REPO_DETAILS_BOTTOM_NAV_ROUTE = "$REPO_DETAILS_ROUTE/{$REPO_NAME_PARAM}"
}