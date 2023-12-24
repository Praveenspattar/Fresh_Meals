package com.myapps.fresh_meals.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapps.fresh_meals.model.Firebase.FavouriteData
import com.myapps.fresh_meals.repository.DatabaseRepository
import kotlinx.coroutines.launch

/**to upload data*/
class DatabaseViewModel(private val databaseRepository: DatabaseRepository) : ViewModel() {

    private val _uploadResult = MutableLiveData<Result<Unit>>()
    val uploadResult: LiveData<Result<Unit>> get() = _uploadResult

    fun uploadFavouriteData(favouriteData: FavouriteData) {
        viewModelScope.launch {
            _uploadResult.value = databaseRepository.uploadFavouriteData(favouriteData)
        }
    }
}