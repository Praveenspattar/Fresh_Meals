package com.myapps.fresh_meals.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myapps.fresh_meals.repository.MealsRepository

class viewModelFactory(val repository: MealsRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MealsViewModel(repository) as T
    }
}