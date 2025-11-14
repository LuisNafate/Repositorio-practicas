package com.luisnafate.practica14_11_2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.luisnafate.practica14_11_2025.data.StudentDatabase
import com.luisnafate.practica14_11_2025.ui.AppNavigation
import com.luisnafate.practica14_11_2025.viewmodel.StudentViewModel
import com.luisnafate.practica14_11_2025.ui.theme.Practica14112025Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practica14112025Theme {
                val database = StudentDatabase.getDatabase(this)
                val viewModel: StudentViewModel by viewModels {
                    StudentViewModel.Factory(database.studentDao())
                }
                AppNavigation(viewModel = viewModel)
            }
        }
    }
}
