package com.luisnafate.practica14_11_2025.ui.stats

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luisnafate.practica14_11_2025.viewmodel.StudentViewModel

@Composable
fun StatsScreen(viewModel: StudentViewModel) {
    val students by viewModel.allStudents.collectAsState(initial = emptyList())
    val averageScore = students.map { it.puntaje }.average().takeIf { !it.isNaN() } ?: 0.0
    val topStudent = students.maxByOrNull { it.puntaje }
    var selectedGroup by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Título
        Text(
            "Estadísticas",
            style = MaterialTheme.typography.titleLarge
        )

        // Card de Promedio General
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Promedio General",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "%.2f puntos".format(averageScore),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "Basado en ${students.size} estudiante(s)",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Card de Mejor Estudiante
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Mejor Estudiante",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                if (topStudent != null) {
                    Text(
                        "${topStudent.nombre} ${topStudent.apellidos}",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Puntaje: ${topStudent.puntaje} pts",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        "Grado: ${topStudent.grado} | Grupo: ${topStudent.grupo}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                } else {
                    Text(
                        "No hay estudiantes registrados",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // Card de Top 3 por Grupo
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Top 3 por Grupo",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(12.dp))

                val groups = students.map { it.grupo }.distinct().sorted()

                if (groups.isNotEmpty()) {
                    Text(
                        "Selecciona un grupo:",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Botones de grupos
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        groups.forEach { grupo ->
                            Button(
                                onClick = { selectedGroup = grupo },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedGroup == grupo)
                                        MaterialTheme.colorScheme.tertiary
                                    else MaterialTheme.colorScheme.primary
                                ),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(grupo)
                            }
                        }
                    }

                    // Mostrar Top 3 del grupo seleccionado
                    if (selectedGroup.isNotEmpty()) {
                        val top3 = students
                            .filter { it.grupo == selectedGroup }
                            .sortedByDescending { it.puntaje }
                            .take(3)

                        Spacer(modifier = Modifier.height(16.dp))
                        HorizontalDivider()
                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            "Grupo $selectedGroup",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        if (top3.isNotEmpty()) {
                            top3.forEachIndexed { index, student ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        "${index + 1}. ${student.nombre} ${student.apellidos}",
                                        style = MaterialTheme.typography.bodyLarge,
                                        modifier = Modifier.weight(1f)
                                    )
                                    Text(
                                        "${student.puntaje} pts",
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        } else {
                            Text(
                                "No hay estudiantes en este grupo",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            "Selecciona un grupo para ver el top 3",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                } else {
                    Text(
                        "No hay grupos disponibles",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // Información adicional
        if (students.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Información General",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Total de estudiantes: ${students.size}")
                    Text("Total de grupos: ${students.map { it.grupo }.distinct().size}")
                    val maxScore = students.maxOfOrNull { it.puntaje } ?: 0
                    val minScore = students.minOfOrNull { it.puntaje } ?: 0
                    Text("Puntaje más alto: $maxScore")
                    Text("Puntaje más bajo: $minScore")
                }
            }
        }
    }
}

