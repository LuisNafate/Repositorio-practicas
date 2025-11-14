package com.luisnafate.practica14_11_2025.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luisnafate.practica14_11_2025.data.Student
import com.luisnafate.practica14_11_2025.viewmodel.StudentViewModel
import androidx.navigation.NavHostController
import com.luisnafate.practica14_11_2025.ui.AppScreen

@Composable
fun DashboardScreen(viewModel: StudentViewModel, navController: NavHostController) {
    val students by viewModel.allStudents.collectAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Lista de Estudiantes", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(students.size) { index ->
                val student = students[index]
                StudentItem(
                    student = student,
                    onDelete = { viewModel.deleteStudent(student) },
                    onEdit = { navController.navigate(AppScreen.EditStudent.createRoute(student.id)) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate(AppScreen.AddStudent.route) }) {
            Text("Agregar Estudiante")
        }
    }
}

@Composable
fun StudentItem(student: Student, onDelete: () -> Unit, onEdit: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("${student.nombre} ${student.apellidos}", style = MaterialTheme.typography.bodyLarge)
            Text("Grado: ${student.grado}, Grupo: ${student.grupo}", style = MaterialTheme.typography.bodyMedium)
            Text("Puntaje: ${student.puntaje}", style = MaterialTheme.typography.bodySmall)
        }
        Row {
            Button(onClick = onEdit) {
                Text("Editar")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onDelete) {
                Text("Eliminar")
            }
        }
    }
}
