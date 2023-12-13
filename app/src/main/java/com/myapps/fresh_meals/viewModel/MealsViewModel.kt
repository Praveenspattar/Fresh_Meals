package com.myapps.fresh_meals.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapps.fresh_meals.model.MealCategory.MealCategories
import com.myapps.fresh_meals.model.Meals_data
import com.myapps.fresh_meals.model.QueryResponse.QueryResponse
import com.myapps.fresh_meals.model.SingleMeal.Meal
import com.myapps.fresh_meals.model.SingleMeal.SingleMealData
import com.myapps.fresh_meals.repository.MealsRepository
import kotlinx.coroutines.launch

class MealsViewModel(private val repo : MealsRepository): ViewModel() {
    private val mutableLiveData = MutableLiveData<Meals_data>()
    private val mutableLiveData2 = MutableLiveData<MealCategories>()
    private val mutableDataQuery = MutableLiveData<QueryResponse>()
    private val mutableMealDetail = MutableLiveData<SingleMealData>()

    val liveData : LiveData<Meals_data> = mutableLiveData
    val liveDataCategory : LiveData<MealCategories> = mutableLiveData2
    val liveDataQuery : LiveData<QueryResponse> = mutableDataQuery
    val liveDataMeal : LiveData<SingleMealData> = mutableMealDetail

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

    fun getQueryResponse(query : String) = viewModelScope.launch {
        try {
            repo.getQueryResponse(query).let {
                if (it.isSuccessful){
                    mutableDataQuery.postValue(it.body())
                }
            }
        }catch (e : Exception){
            Log.e("error in view model", "get query : ${e.message.toString()}")
        }
    }

    fun getMealDetail(idMeal : String) = viewModelScope.launch {
        try {
            repo.getMealDetail(idMeal).let {
                if (it.isSuccessful){
                    mutableMealDetail.postValue(it.body())
                }
            }
        }catch (e :Exception){
            Log.e("error in view model ","get Meal id : ${e.message.toString()}")
        }
    }

}