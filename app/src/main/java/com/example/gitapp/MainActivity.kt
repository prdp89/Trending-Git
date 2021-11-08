package com.example.gitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gitapp.bottomnavigation.BottomNav
import com.example.gitapp.bottomnavigation.GitBottomNavigation
import com.example.gitapp.bottomnavigation.destinations.SearchBottomNavRoute
import com.example.gitapp.ui.theme.GitAppTheme
import com.example.navigator.Navigator
import com.example.navigator.NavigatorEvent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitAppTheme {
                // A surface container using the 'background' color from the theme
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
                    startDestination = SearchBottomNavRoute.route,
                    builder = {
                        addSearch()
                        //addFavorites()
                        //addSettings()
                    }
                )
            }
        }

    private fun NavGraphBuilder.addSearch() {
        composable(SearchBottomNavRoute.route) {
            Search()
        }
    }

    @Composable
    @OptIn(ExperimentalMaterialApi::class)
    fun Search() {
        Text(text = "Search here...")
    }
}
