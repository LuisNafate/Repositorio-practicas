package com.luisnafate.examen2.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.luisnafate.examen2.data.AppDatabase
import com.luisnafate.examen2.data.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class UserDataViewModel(app: Application) : AndroidViewModel(app) {
    private val dao = AppDatabase.getDatabase(app).userDataDao()

    // Estados
    val nombre = MutableStateFlow("")
    val email = MutableStateFlow("")
    val telefono = MutableStateFlow("")

    init {
        // Cargar datos guardados
        viewModelScope.launch {
            dao.getUserData().collect { userData ->
                if (userData != null) {
                    nombre.value = userData.nombre
                    email.value = userData.email
                    telefono.value = userData.telefono
                }
            }
        }
    }

    // Guardar datos
    fun save() {
        viewModelScope.launch {
            val user = UserData(nombre = nombre.value, email = email.value, telefono = telefono.value)
            dao.insertUserData(user)
        }
    }

    // Obtener datos formateados para mostrar en toast
    fun getDataAsString(): String {
        return if (nombre.value.isEmpty() && email.value.isEmpty() && telefono.value.isEmpty()) {
            "No hay datos guardados"
        } else {
            "Nombre: ${nombre.value}\nEmail: ${email.value}\nTeléfono: ${telefono.value}"
        }
    }
}

