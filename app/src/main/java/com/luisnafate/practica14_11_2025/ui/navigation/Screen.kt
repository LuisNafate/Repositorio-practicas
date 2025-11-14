package com.luisnafate.practica14_11_2025.ui

sealed class Screen(val route: String) {
    object Dashboard : Screen("dashboard")
    object AddStudent : Screen("add_student")
    object EditStudent : Screen("edit_student/{studentId}") {
        fun createRoute(studentId: Int) = "edit_student/$studentId"
    }
}
