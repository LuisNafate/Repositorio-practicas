package com.luisnafate.examen3.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.luisnafate.examen3.data.local.F1Database
import com.luisnafate.examen3.data.network.RetrofitClient
import com.luisnafate.examen3.data.repository.F1Repository

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    
    private val repository: F1Repository by lazy {
        F1Repository(
            api = RetrofitClient.apiService,
            database = F1Database.getDatabase(context)
        )
    }
    
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> {
                SplashViewModel(repository) as T
            }
            modelClass.isAssignableFrom(RaceListViewModel::class.java) -> {
                RaceListViewModel(repository) as T
            }
            modelClass.isAssignableFrom(RaceDetailViewModel::class.java) -> {
                RaceDetailViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> {
                SearchViewModel(repository) as T
            }
            modelClass.isAssignableFrom(FavoritesViewModel::class.java) -> {
                FavoritesViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
