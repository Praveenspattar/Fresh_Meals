package com.myapps.fresh_meals.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapps.fresh_meals.model.MealCategory.MealCategories
import com.myapps.fresh_meals.model.Meals_data
import com.myapps.fresh_meals.repository.MealsRepository
import kotlinx.coroutines.launch

class mealsViewModel(val repo : MealsRepository): ViewModel() {
    private val mutableLiveData = MutableLiveData<Meals_data>()
    private val mutableLiveData2 = MutableLiveData<MealCategories>()

    val liveData : LiveData<Meals_data> = mutableLiveData
    val liveDataCategory : LiveData<MealCategories> = mutableLiveData2

    init {
        getMealData()
        getMealCategory()
    }

    private fun getMealData() = viewModelScope.launch {
        try {
            repo.getmealData().let { respose->
                if (respose.isSuccessful){
                    mutableLiveData.postValue(respose.body())
                }
            }
        }catch (e: Exception){
            Log.e("error in ViewModel", "get meals :${e.message.toString()}")
        }
    }

    private fun getMealCategory() = viewModelScope.launch {
        try {
            repo.getCategoryData().let {
                if (it.isSuccessful){
                    mutableLiveData2.postValue(it.body())
                }
            }
        }catch (e : Exception){
            Log.e("error inview Model", "get category : ${e.message.toString()}")
        }
    }

}