package com.sample.gitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.sample.bottomnavigation.BottomNav
import com.sample.bottomnavigation.GitAppBottomNavigation
import com.sample.bottomnavigation.destinations.DevsBottomNavRoute
import com.sample.bottomnavigation.destinations.RepoBottomNavRoute
import com.sample.bottomnavigation.destinations.SettingsBottomNavRoute
import com.sample.devsui.DevsListUi
import com.sample.gitapp.ui.theme.GitAppTheme
import com.sample.navigator.Navigator
import com.sample.navigator.NavigatorEvent
import com.sample.reposui.RepoListUi
import com.sample.settingsui.SettingsUi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import java.util.concurrent.Flow
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            GitAppTheme {
                ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
                    Surface(color = MaterialTheme.colors.background) {
                        GitAppScaffold(navigator)
                    }
                }
            }
        }
    }
}

@Composable
private fun GitAppScaffold(navigator: Navigator) {
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
                GitAppBottomNavigation(navController, BottomNav.bottomNavigationEntries)
            }
        ) {
            NavHost(
                navController = navController,
                startDestination = RepoBottomNavRoute.route,
                builder = {
                    addRepos()
                    addDevs()
                    addSettings()
                }
            )
        }

}

private fun NavGraphBuilder.addSettings() {
    composable(SettingsBottomNavRoute.route) {
        SettingsUi()
    }
}

private fun NavGraphBuilder.addDevs() {
    composable(DevsBottomNavRoute.route) {
        DevsListUi()
    }
}

private fun NavGraphBuilder.addRepos() {
    composable(RepoBottomNavRoute.route) {
        //RepoListUi()
        Column {
            Text(text ="This is List screen", color = Color.Blue)
        }
    }
}
