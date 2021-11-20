package com.example.gitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.devsui.DevsScreen
import com.example.gitapp.bottomnavigation.BottomNav
import com.example.gitapp.bottomnavigation.GitBottomNavigation
import com.example.gitapp.bottomnavigation.destinations.DevBottomNavRoute
import com.example.gitapp.bottomnavigation.destinations.RepoBottomNavRoute
import com.example.gitapp.bottomnavigation.destinations.FavoriteBottomNavRoute
import com.example.gitapp.bottomnavigation.destinations.SettingsBottomNavRoute
import com.example.navigator.Navigator
import com.example.navigator.NavigatorEvent
import com.example.reposui.ReposScreen
import com.example.favoriteui.FavoriteScreen
import com.example.settings.SettingsViewModel
import com.example.settings.ui.SettingsScreen
import com.example.style.GitAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    private val isDarkThemeEnabled get() = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitAppTheme(darkThemeFlow = hiltViewModel<SettingsViewModel>().darkTheme, isDarkThemeEnabled) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GitScaffold(navigator = navigator)
                }
            }
        }
    }

    @Composable
    fun GitScaffold(navigator: Navigator) {
        val navController = rememberNavController()
        LaunchedEffect(navController) {
              navigator.destinations.collect {
                when (val event = it) {
                    is NavigatorEvent.NavigateUp -> navController.navigateUp()
                    is NavigatorEvent.Directions -> navController.navigate(event.destination, event.builder)
                }
            }
        }
            Scaffold(
                bottomBar = {
                    GitBottomNavigation(navController, BottomNav.bottomNavigationEntries)
                }
            ) {
                NavHost(
                    navController = navController,
                    startDestination = RepoBottomNavRoute.route,
                    builder = {
                        addRepos()
                        addFavorite()
                        addDevs()
                        addSettings()
                    }
                )
            }
        }

    private fun NavGraphBuilder.addRepos() {
        composable(RepoBottomNavRoute.route) {
            ReposScreen()
        }
    }

    private fun NavGraphBuilder.addDevs() {
        composable(DevBottomNavRoute.route) {
            DevsScreen()
        }
    }

    private fun NavGraphBuilder.addFavorite() {
        composable(FavoriteBottomNavRoute.route) {
            FavoriteScreen()
        }
    }

    private fun NavGraphBuilder.addSettings() {
        composable(SettingsBottomNavRoute.route) {
            SettingsScreen()
        }
    }
}
