package com.myapps.fresh_meals.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myapps.fresh_meals.repository.FetchDataRepo

class FetchModelFactory(var repo : FetchDataRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FetchDataViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FetchDataViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}