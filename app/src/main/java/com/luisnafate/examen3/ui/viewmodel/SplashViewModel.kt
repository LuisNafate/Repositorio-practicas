package com.luisnafate.examen3.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisnafate.examen3.data.model.Race
import com.luisnafate.examen3.data.repository.F1Repository
import com.luisnafate.examen3.ui.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel(private val repository: F1Repository) : ViewModel() {
    
    private val _uiState = MutableStateFlow<UiState<List<Race>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Race>>> = _uiState.asStateFlow()
    
    init {
        loadInitialData()
    }
    
    private fun loadInitialData() {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                val races = repository.getCurrentSeasonRaces()
                _uiState.value = UiState.Success(races)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(
                    e.message ?: "Error al cargar datos"
                )
            }
        }
    }
}
