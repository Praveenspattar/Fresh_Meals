package com.myapps.fresh_meals.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myapps.fresh_meals.repository.FireDataRepository

class FireDataModelFactory (private val repository: FireDataRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FireDataViewModel::class.java)) {
            return FireDataViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}