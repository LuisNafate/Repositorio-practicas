package com.luisnafate.examen3.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisnafate.examen3.data.model.Driver
import com.luisnafate.examen3.data.repository.F1Repository
import com.luisnafate.examen3.ui.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: F1Repository) : ViewModel() {
    
    private val _uiState = MutableStateFlow<UiState<List<Driver>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Driver>>> = _uiState.asStateFlow()
    
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    
    private val _allDrivers = mutableListOf<Driver>()
    
    init {
        loadDrivers()
    }
    
    private fun loadDrivers() {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                val drivers = repository.getCurrentDrivers()
                _allDrivers.clear()
                _allDrivers.addAll(drivers)
                _uiState.value = UiState.Success(drivers)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(
                    e.message ?: "Error al cargar pilotos"
                )
            }
        }
    }
    
    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        filterDrivers(query)
    }
    
    private fun filterDrivers(query: String) {
        if (query.isBlank()) {
            _uiState.value = UiState.Success(_allDrivers)
            return
        }
        
        val filtered = _allDrivers.filter { driver ->
            driver.fullName.contains(query, ignoreCase = true) ||
            driver.nationality.contains(query, ignoreCase = true) ||
            driver.code?.contains(query, ignoreCase = true) == true
        }
        _uiState.value = UiState.Success(filtered)
    }
    
    suspend fun toggleFavorite(driver: Driver): Boolean {
        return try {
            val isFavorite = repository.isDriverFavorite(driver.driverId)
            if (isFavorite) {
                repository.removeFavoriteDriver(driver.driverId)
            } else {
                repository.addFavoriteDriver(driver)
            }
            !isFavorite
        } catch (e: Exception) {
            false
        }
    }
    
    suspend fun isDriverFavorite(driverId: String): Boolean {
        return repository.isDriverFavorite(driverId)
    }
}
