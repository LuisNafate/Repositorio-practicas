package com.luisnafate.examen2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.luisnafate.examen2.Presentation.components.NavManager.NavManager
import com.luisnafate.examen2.ui.theme.Examen2Theme
import com.luisnafate.examen2.ViewModel.ThemeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themeViewModel: ThemeViewModel = viewModel()
            val isDarkTheme by themeViewModel.isDark.collectAsState()

            Examen2Theme(darkTheme = isDarkTheme) {
                NavManager(themeViewModel = themeViewModel)
            }
        }
    }
}

