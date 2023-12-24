package com.myapps.fresh_meals.viewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myapps.fresh_meals.repository.DatabaseRepository

class DatabaseViewModelFactory(private val databaseRepository: DatabaseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DatabaseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DatabaseViewModel(databaseRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}