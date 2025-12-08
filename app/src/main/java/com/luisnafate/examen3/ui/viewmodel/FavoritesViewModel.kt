package com.luisnafate.examen3.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisnafate.examen3.data.model.Driver
import com.luisnafate.examen3.data.model.Race
import com.luisnafate.examen3.data.repository.F1Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class FavoritesState(
    val favoriteRaces: List<Race> = emptyList(),
    val favoriteDrivers: List<Driver> = emptyList(),
    val selectedTab: Int = 0
)

class FavoritesViewModel(private val repository: F1Repository) : ViewModel() {
    
    private val _state = MutableStateFlow(FavoritesState())
    val state: StateFlow<FavoritesState> = _state.asStateFlow()
    
    init {
        loadFavorites()
    }
    
    private fun loadFavorites() {
        viewModelScope.launch {
            repository.getFavoriteRaces().collect { races ->
                _state.value = _state.value.copy(favoriteRaces = races)
            }
        }
        
        viewModelScope.launch {
            repository.getFavoriteDrivers().collect { drivers ->
                _state.value = _state.value.copy(favoriteDrivers = drivers)
            }
        }
    }
    
    fun onTabSelected(index: Int) {
        _state.value = _state.value.copy(selectedTab = index)
    }
    
    suspend fun removeFavoriteRace(uniqueId: String) {
        repository.removeFavoriteRace(uniqueId)
    }
    
    suspend fun removeFavoriteDriver(driverId: String) {
        repository.removeFavoriteDriver(driverId)
    }
}
