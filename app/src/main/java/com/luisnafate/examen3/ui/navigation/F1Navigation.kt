package com.luisnafate.examen3.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.luisnafate.examen3.ui.screen.*
import com.luisnafate.examen3.ui.viewmodel.*

@Composable
fun F1Navigation(
    viewModelFactory: ViewModelFactory
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    
    Scaffold(
        bottomBar = {
            if (currentRoute in listOf(
                    Screen.RaceList.route,
                    Screen.Search.route,
                    Screen.Favorites.route
                )
            ) {
                BottomNavigationBar(
                    currentRoute = currentRoute,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            navController.graph.startDestinationRoute?.let { startRoute ->
                                popUpTo(startRoute) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Splash.route) {
                val viewModel: SplashViewModel = viewModel(factory = viewModelFactory)
                SplashScreen(
                    viewModel = viewModel,
                    onNavigateToRaceList = {
                        navController.navigate(Screen.RaceList.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
                    }
                )
            }
            
            composable(Screen.RaceList.route) {
                val viewModel: RaceListViewModel = viewModel(factory = viewModelFactory)
                RaceListScreen(
                    viewModel = viewModel,
                    onRaceClick = { season, round ->
                        navController.navigate(Screen.RaceDetail.createRoute(season, round))
                    }
                )
            }
            
            composable(
                route = Screen.RaceDetail.route,
                arguments = listOf(
                    navArgument("season") { type = NavType.StringType },
                    navArgument("round") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val season = backStackEntry.arguments?.getString("season") ?: ""
                val round = backStackEntry.arguments?.getString("round") ?: ""
                val viewModel: RaceDetailViewModel = viewModel(factory = viewModelFactory)
                
                RaceDetailScreen(
                    season = season,
                    round = round,
                    viewModel = viewModel,
                    onNavigateBack = { navController.popBackStack() }
                )
            }
            
            composable(Screen.Search.route) {
                val viewModel: SearchViewModel = viewModel(factory = viewModelFactory)
                SearchScreen(
                    viewModel = viewModel,
                    onDriverClick = { driverId -> }
                )
            }
            
            composable(Screen.Favorites.route) {
                val viewModel: FavoritesViewModel = viewModel(factory = viewModelFactory)
                FavoritesScreen(
                    viewModel = viewModel,
                    onRaceClick = { season, round ->
                        navController.navigate(Screen.RaceDetail.createRoute(season, round))
                    },
                    onDriverClick = { driverId -> }
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Flag, contentDescription = null) },
            label = { Text("Carreras") },
            selected = currentRoute == Screen.RaceList.route,
            onClick = { onNavigate(Screen.RaceList.route) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Search, contentDescription = null) },
            label = { Text("Buscar") },
            selected = currentRoute == Screen.Search.route,
            onClick = { onNavigate(Screen.Search.route) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
            label = { Text("Favoritos") },
            selected = currentRoute == Screen.Favorites.route,
            onClick = { onNavigate(Screen.Favorites.route) }
        )
    }
}
