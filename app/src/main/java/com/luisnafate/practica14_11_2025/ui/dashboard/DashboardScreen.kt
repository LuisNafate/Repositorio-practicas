package com.luisnafate.practica14_11_2025.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luisnafate.practica14_11_2025.data.Student
import com.luisnafate.practica14_11_2025.viewmodel.StudentViewModel
import androidx.navigation.NavHostController
import com.luisnafate.practica14_11_2025.ui.AppScreen

@Composable
fun DashboardScreen(viewModel: StudentViewModel, navController: NavHostController) {
    val students by viewModel.allStudents.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Título y contador
        Text(
            "Lista de Estudiantes (${students.size})",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Lista de estudiantes
        if (students.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "No hay estudiantes.\n¡Agrega uno en el tab 'Agregar'!",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(students) { student ->
                    StudentItem(
                        student = student,
                        onDelete = { viewModel.deleteStudent(student) },
                        onEdit = { navController.navigate(AppScreen.EditStudent.createRoute(student.id)) }
                    )
                }
            }
        }
    }
}

@Composable
fun StudentItem(student: Student, onDelete: () -> Unit, onEdit: () -> Unit) {
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
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    "${student.nombre} ${student.apellidos}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    "Grado: ${student.grado} | Grupo: ${student.grupo}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    "Puntaje: ${student.puntaje}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                IconButton(onClick = onEdit) {
                    Text("✏️")
                }
                IconButton(onClick = onDelete) {
                    Text("🗑️")
                }
            }
        }
    }
}
