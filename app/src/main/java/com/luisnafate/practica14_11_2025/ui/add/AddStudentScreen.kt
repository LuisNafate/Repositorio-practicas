package com.luisnafate.practica14_11_2025.ui.add

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
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
    var showError by remember { mutableStateOf(false) }
    var showSuccess by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Nuevo Estudiante",
                    style = MaterialTheme.typography.titleLarge
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
                "⚠Por favor, completa todos los campos correctamente",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        if (showSuccess) {
            Text(
                "Estudiante agregado exitosamente",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                if (nombre.isBlank() || apellidos.isBlank() || grado.isBlank() ||
                    grupo.isBlank() || puntaje.toIntOrNull() == null) {
                    showError = true
                    showSuccess = false
                } else {
                    val student = Student(
                        nombre = nombre.trim(),
                        apellidos = apellidos.trim(),
                        grado = grado.trim(),
                        grupo = grupo.trim().uppercase(),
                        puntaje = puntaje.toInt()
                    )
                    viewModel.addStudent(student)
                    showSuccess = true
                    showError = false
                    // Limpiar campos
                    nombre = ""
                    apellidos = ""
                    grado = ""
                    grupo = ""
                    puntaje = ""
                    // Notificar que se agregó
                    onStudentAdded()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar Estudiante")
        }

        OutlinedButton(
            onClick = {
                // Limpiar campos
                nombre = ""
                apellidos = ""
                grado = ""
                grupo = ""
                puntaje = ""
                showError = false
                showSuccess = false
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Limpiar Campos")
        }
    }
}
