package com.luisnafate.examen2.Presentation.components.NavManager

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.luisnafate.examen2.Presentation.Views.View1
import com.luisnafate.examen2.Presentation.Views.View2
import com.luisnafate.examen2.Presentation.Views.View3
import com.luisnafate.examen2.ViewModel.ThemeViewModel

@Composable
fun NavManager(themeViewModel: ThemeViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "view1") {
        composable("view1") { View1(navController) }
        composable("view2") { View2(navController, themeViewModel) }
        composable("view3") { View3(navController) }
    }
}

