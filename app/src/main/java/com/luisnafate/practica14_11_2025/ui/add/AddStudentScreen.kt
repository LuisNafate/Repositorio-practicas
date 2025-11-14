package com.luisnafate.practica14_11_2025.ui.add

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luisnafate.practica14_11_2025.data.Student
import com.luisnafate.practica14_11_2025.viewmodel.StudentViewModel

@Composable
fun AddStudentScreen(viewModel: StudentViewModel, onStudentAdded: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var grado by remember { mutableStateOf("") }
    var grupo by remember { mutableStateOf("") }
    var puntaje by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Agregar Estudiante", style = MaterialTheme.typography.titleLarge)
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
            val student = Student(
                nombre = nombre,
                apellidos = apellidos,
                grado = grado,
                grupo = grupo,
                puntaje = puntaje.toIntOrNull() ?: 0
            )
            viewModel.addStudent(student)
            onStudentAdded()
        }) {
            Text("Guardar")
        }
    }
}
