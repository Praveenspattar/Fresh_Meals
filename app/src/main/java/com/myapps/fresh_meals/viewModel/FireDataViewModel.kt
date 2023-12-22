package com.myapps.fresh_meals.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapps.fresh_meals.model.Firebase.FavouriteData
import com.myapps.fresh_meals.repository.FireDataRepository
import kotlinx.coroutines.launch

class FireDataViewModel(private val repository: FireDataRepository) : ViewModel() {

    private val _data = MutableLiveData<Result<List<FavouriteData>>>()
    val data: LiveData<Result<List<FavouriteData>>> get() = _data

    fun fetchData() {
        viewModelScope.launch {
            _data.value = repository.getData()
        }
    }
}