package com.luisnafate.examen2.Presentation.Views

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.luisnafate.examen2.ViewModel.ThemeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun View2(navController: NavController, themeViewModel: ThemeViewModel) {
    val isDarkTheme by themeViewModel.isDark.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tema") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Cambiar Tema", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = if (isDarkTheme) "🌙" else "☀️",
                            style = MaterialTheme.typography.headlineLarge
                        )
                        Text(text = if (isDarkTheme) "Modo Oscuro" else "Modo Claro")
                    }
                    Switch(
                        checked = isDarkTheme,
                        onCheckedChange = { themeViewModel.toggle() }
                    )
                }
            }
        }
    }
}

