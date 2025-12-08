package com.luisnafate.examen3.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisnafate.examen3.data.model.RaceResult
import com.luisnafate.examen3.data.repository.F1Repository
import com.luisnafate.examen3.ui.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RaceDetailViewModel(private val repository: F1Repository) : ViewModel() {
    
    private val _uiState = MutableStateFlow<UiState<List<RaceResult>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<RaceResult>>> = _uiState.asStateFlow()
    
    fun loadRaceResults(season: String, round: String) {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                val seasonInt = season.toIntOrNull() ?: 2024
                val roundInt = round.toIntOrNull() ?: 1
                val results = repository.getRaceResults(seasonInt, roundInt)
                _uiState.value = UiState.Success(results)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(
                    e.message ?: "Error al cargar resultados"
                )
            }
        }
    }
}
