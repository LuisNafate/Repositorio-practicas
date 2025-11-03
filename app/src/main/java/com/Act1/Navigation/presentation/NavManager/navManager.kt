package com.Act1.Navigation.presentation.NavManager

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.Act1.Navigation.presentation.views.DetailsView
import com.Act1.Navigation.presentation.views.DetailsView2
import com.Act1.Navigation.presentation.views.DetailsView3
import com.Act1.Navigation.presentation.views.HomeView

/***
 * Project: CuartoA2
 * Package: com.danielflores.cuartoa2.presentation.NavManager
 * Created by Kevin Daniel Flores Nataren
 * File created at 10/October/2025 at 14:29
 * All rights reserved 2025.
 **/

@Composable
fun NavManager(){
    val navController = rememberNavController()
    val id1= 1L
    val id2= 2L
    val id3= 3L
    NavHost(
        navController = navController,
        startDestination = "Home"
    ){
        composable("Home") {
            HomeView(navController)
        }
        composable("Details") {
            DetailsView(navController)

        }
        composable ("Details2"){
            DetailsView2(navController)
        }
        composable ("Details3"){
            DetailsView3(navController)
        }
    }
}