package com.luisnafate.practica14_11_2025.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val apellidos: String,
    val grado: String,
    val grupo: String,
    val puntaje: Int
)
