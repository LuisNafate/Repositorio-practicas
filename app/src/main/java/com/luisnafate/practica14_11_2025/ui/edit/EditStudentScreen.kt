package com.luisnafate.practica14_11_2025.ui.edit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.luisnafate.practica14_11_2025.data.Student
import com.luisnafate.practica14_11_2025.viewmodel.StudentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditStudentScreen(viewModel: StudentViewModel, student: Student, onStudentUpdated: () -> Unit) {
    var nombre by remember { mutableStateOf(student.nombre) }
    var apellidos by remember { mutableStateOf(student.apellidos) }
    var grado by remember { mutableStateOf(student.grado) }
    var grupo by remember { mutableStateOf(student.grupo) }
    var puntaje by remember { mutableStateOf(student.puntaje.toString()) }
    var showError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar Estudiante") },
                navigationIcon = {
                    IconButton(onClick = onStudentUpdated) {
                        Text("←")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "✏️ Editando: ${student.nombre} ${student.apellidos}",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = showError && nombre.isBlank()
            )

            OutlinedTextField(
                value = apellidos,
                onValueChange = { apellidos = it },
                label = { Text("Apellidos") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = showError && apellidos.isBlank()
            )

            OutlinedTextField(
                value = grado,
                onValueChange = { grado = it },
                label = { Text("Grado (ej: 1°, 2°, 3°)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = showError && grado.isBlank()
            )

            OutlinedTextField(
                value = grupo,
                onValueChange = { grupo = it },
                label = { Text("Grupo (ej: A, B, C)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = showError && grupo.isBlank()
            )

            OutlinedTextField(
                value = puntaje,
                onValueChange = { puntaje = it.filter { char -> char.isDigit() } },
                label = { Text("Puntaje (0-100)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                isError = showError && (puntaje.isBlank() || puntaje.toIntOrNull() == null)
            )

            if (showError) {
                Text(
                    "⚠️ Por favor, completa todos los campos correctamente",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (nombre.isBlank() || apellidos.isBlank() || grado.isBlank() ||
                        grupo.isBlank() || puntaje.toIntOrNull() == null) {
                        showError = true
                    } else {
                        val updatedStudent = student.copy(
                            nombre = nombre.trim(),
                            apellidos = apellidos.trim(),
                            grado = grado.trim(),
                            grupo = grupo.trim().uppercase(),
                            puntaje = puntaje.toInt()
                        )
                        viewModel.updateStudent(updatedStudent)
                        onStudentUpdated()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Actualizar Estudiante")
            }

            OutlinedButton(
                onClick = onStudentUpdated,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cancelar")
            }
        }
    }
}
