package com.luisnafate.practica14_11_2025.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.luisnafate.practica14_11_2025.ui.add.AddStudentScreen
import com.luisnafate.practica14_11_2025.ui.dashboard.DashboardScreen
import com.luisnafate.practica14_11_2025.ui.edit.EditStudentScreen
import com.luisnafate.practica14_11_2025.ui.stats.StatsScreen
import com.luisnafate.practica14_11_2025.viewmodel.StudentViewModel

sealed class AppScreen(val route: String) {
    object Main : AppScreen("main")
    object EditStudent : AppScreen("edit_student/{studentId}") {
        fun createRoute(studentId: Int) = "edit_student/$studentId"
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(viewModel: StudentViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreen.Main.route) {
        composable(AppScreen.Main.route) {
            MainScreenWithTabs(viewModel = viewModel, navController = navController)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenWithTabs(viewModel: StudentViewModel, navController: androidx.navigation.NavHostController) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Dashboard", "Agregar", "Estadísticas")

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text("Gestión de Estudiantes") }
                )
                TabRow(selectedTabIndex = selectedTabIndex) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            text = { Text(title) }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedTabIndex) {
                0 -> DashboardScreen(viewModel = viewModel, navController = navController)
                1 -> AddStudentScreen(viewModel = viewModel, onStudentAdded = {
                    selectedTabIndex = 0 // Volver al dashboard después de agregar
                })
                2 -> StatsScreen(viewModel = viewModel)
            }
        }
    }
}

