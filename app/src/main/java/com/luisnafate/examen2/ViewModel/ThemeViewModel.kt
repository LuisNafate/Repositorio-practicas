package com.luisnafate.examen2.ViewModel

import android.app.Application
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

private val Application.dataStore by preferencesDataStore("theme")

class ThemeViewModel(app: Application) : AndroidViewModel(app) {
    private val themeKey = booleanPreferencesKey("isDark")

    val isDark = app.dataStore.data.map { it[themeKey] ?: false }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    fun toggle() {
        viewModelScope.launch {
            getApplication<Application>().dataStore.edit { it[themeKey] = !isDark.value }
        }
    }
}

