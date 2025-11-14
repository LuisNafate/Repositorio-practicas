package com.luisnafate.practica14_11_2025.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.luisnafate.practica14_11_2025.ui.add.AddStudentScreen
import com.luisnafate.practica14_11_2025.ui.dashboard.DashboardScreen
import com.luisnafate.practica14_11_2025.ui.edit.EditStudentScreen
import com.luisnafate.practica14_11_2025.viewmodel.StudentViewModel

sealed class AppScreen(val route: String) {
    object Dashboard : AppScreen("dashboard")
    object AddStudent : AppScreen("add_student")
    object EditStudent : AppScreen("edit_student/{studentId}") {
        fun createRoute(studentId: Int) = "edit_student/$studentId"
    }
}

@Composable
fun AppNavigation(viewModel: StudentViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreen.Dashboard.route) {
        composable(AppScreen.Dashboard.route) {
            DashboardScreen(viewModel = viewModel, navController = navController)
        }
        composable(AppScreen.AddStudent.route) {
            AddStudentScreen(viewModel = viewModel) {
                navController.popBackStack()
            }
        }
        composable(AppScreen.EditStudent.route) { backStackEntry ->
            val studentId = backStackEntry.arguments?.getString("studentId")?.toIntOrNull()
            val student = viewModel.allStudents.collectAsState(initial = emptyList()).value.find { it.id == studentId }
            if (student != null) {
                EditStudentScreen(viewModel = viewModel, student = student) {
                    navController.popBackStack()
                }
            }
        }
    }
}
