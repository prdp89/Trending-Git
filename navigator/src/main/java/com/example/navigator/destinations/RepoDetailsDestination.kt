package com.example.navigator.destinations

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument
import com.example.navigator.NavigationDestination

object RepoDetailsDestination : NavigationDestination {
    override fun route(): String = REPO_DETAILS_BOTTOM_NAV_ROUTE

    override val arguments: List<NamedNavArgument>
        get() = listOf(navArgument(REPO_NAME_PARAM) { type = NavType.StringType },
        navArgument(REPO_ID_PARAM) { type = NavType.IntType
                                    defaultValue = 0})

    fun createRepoDetailsRoute(repoName: String, repoID: Int, repoAuthor: String) =
                                "$REPO_DETAILS_ROUTE/${repoName}/${repoID}/${repoAuthor}"

    const val REPO_NAME_PARAM = "repo"
    const val REPO_ID_PARAM = "id"
    const val REPO_AUTHOR_PARAM = "author"
    private const val REPO_DETAILS_ROUTE = "repo_details"
    private const val REPO_DETAILS_BOTTOM_NAV_ROUTE = "$REPO_DETAILS_ROUTE/{$REPO_NAME_PARAM}/{$REPO_ID_PARAM}/{$REPO_AUTHOR_PARAM}"
}