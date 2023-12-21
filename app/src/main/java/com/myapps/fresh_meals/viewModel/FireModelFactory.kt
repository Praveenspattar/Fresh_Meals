package com.myapps.fresh_meals.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myapps.fresh_meals.repository.FireRepository

class FireModelFactory(private val fireRepository: FireRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FireViewModel(fireRepository) as T
    }
}