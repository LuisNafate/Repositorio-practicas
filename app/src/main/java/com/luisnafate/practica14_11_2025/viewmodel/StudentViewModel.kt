package com.luisnafate.practica14_11_2025.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.luisnafate.practica14_11_2025.data.Student
import com.luisnafate.practica14_11_2025.data.StudentDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class StudentViewModel(private val studentDao: StudentDao) : ViewModel() {

    val allStudents: Flow<List<Student>> = studentDao.getAllStudents()

    fun addStudent(student: Student) {
        viewModelScope.launch {
            studentDao.insertStudent(student)
        }
    }

    fun updateStudent(student: Student) {
        viewModelScope.launch {
            studentDao.updateStudent(student)
        }
    }

    fun deleteStudent(student: Student) {
        viewModelScope.launch {
            studentDao.deleteStudent(student)
        }
    }

    class Factory(private val studentDao: StudentDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(StudentViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return StudentViewModel(studentDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
