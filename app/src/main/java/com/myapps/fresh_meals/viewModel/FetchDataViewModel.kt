package com.myapps.fresh_meals.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapps.fresh_meals.model.Firebase.FavouriteData
import com.myapps.fresh_meals.repository.FetchDataRepo
import kotlinx.coroutines.launch

class FetchDataViewModel (private val repository: FetchDataRepo) : ViewModel() {
    init {
        fetchData()
    }
    private val _data = MutableLiveData<List<FavouriteData>>()
    val myData: LiveData<List<FavouriteData>> get() = _data

    private fun fetchData() {
        println("iwant viewmodel")
        viewModelScope.launch {
            _data.value = repository.getData()
        }
    }

}