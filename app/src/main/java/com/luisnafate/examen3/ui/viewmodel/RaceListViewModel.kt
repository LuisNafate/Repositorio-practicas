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

class RaceListViewModel(private val repository: F1Repository) : ViewModel() {
    
    private val _uiState = MutableStateFlow<UiState<List<Race>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Race>>> = _uiState.asStateFlow()
    
    private val _allRaces = mutableListOf<Race>()
    
    init {
        loadCurrentSeasonRaces()
    }
    
    fun loadCurrentSeasonRaces() {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                val races = repository.getCurrentSeasonRaces()
                _allRaces.clear()
                _allRaces.addAll(races)
                _uiState.value = UiState.Success(races)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(
                    e.message ?: "Error al cargar carreras"
                )
            }
        }
    }
    
    suspend fun toggleFavorite(race: Race): Boolean {
        return try {
            val isFavorite = repository.isRaceFavorite(race.uniqueId)
            if (isFavorite) {
                repository.removeFavoriteRace(race.uniqueId)
            } else {
                repository.addFavoriteRace(race)
            }
            !isFavorite
        } catch (e: Exception) {
            false
        }
    }
    
    suspend fun isRaceFavorite(uniqueId: String): Boolean {
        return repository.isRaceFavorite(uniqueId)
    }
}
