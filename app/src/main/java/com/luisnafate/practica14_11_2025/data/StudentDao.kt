package com.luisnafate.practica14_11_2025.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Query("SELECT * FROM Student")
    fun getAllStudents(): Flow<List<Student>>

    @Insert
    suspend fun insertStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("SELECT AVG(puntaje) FROM Student")
    fun getAverageScore(): Flow<Double>

    @Query("SELECT * FROM Student ORDER BY puntaje DESC LIMIT 1")
    fun getTopStudent(): Flow<Student>

    @Query("SELECT * FROM Student WHERE grupo = :grupo ORDER BY puntaje DESC LIMIT 3")
    fun getTop3StudentsByGroup(grupo: String): Flow<List<Student>>
}
