package com.myapps.fresh_meals.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FireModelFactory(val email : String, val password : String): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FireViewModel(email,password) as T
    }
}