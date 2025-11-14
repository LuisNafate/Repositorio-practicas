package com.luisnafate.practica14_11_2025.ui.edit

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luisnafate.practica14_11_2025.data.Student
import com.luisnafate.practica14_11_2025.viewmodel.StudentViewModel

@Composable
fun EditStudentScreen(viewModel: StudentViewModel, student: Student, onStudentUpdated: () -> Unit) {
    var nombre by remember { mutableStateOf(student.nombre) }
    var apellidos by remember { mutableStateOf(student.apellidos) }
    var grado by remember { mutableStateOf(student.grado) }
    var grupo by remember { mutableStateOf(student.grupo) }
    var puntaje by remember { mutableStateOf(student.puntaje.toString()) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Editar Estudiante", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = apellidos,
            onValueChange = { apellidos = it },
            label = { Text("Apellidos") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = grado,
            onValueChange = { grado = it },
            label = { Text("Grado") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = grupo,
            onValueChange = { grupo = it },
            label = { Text("Grupo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = puntaje,
            onValueChange = { puntaje = it },
            label = { Text("Puntaje") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val updatedStudent = student.copy(
                nombre = nombre,
                apellidos = apellidos,
                grado = grado,
                grupo = grupo,
                puntaje = puntaje.toIntOrNull() ?: 0
            )
            viewModel.updateStudent(updatedStudent)
            onStudentUpdated()
        }) {
            Text("Actualizar")
        }
    }
}
