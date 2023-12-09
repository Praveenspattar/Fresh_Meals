package com.myapps.fresh_meals.repository

import com.myapps.fresh_meals.Api.Api_Interface

class MealsRepository(val apiServices : Api_Interface) {

//    suspend fun getmealData() = constants.getRetrofitInstant().create(apiServices::class.java)
    suspend fun getmealData() = apiServices.getMealsData()

    suspend fun getCategoryData() = apiServices.getCategoryData()

    suspend fun getQueryResponse(query : String) = apiServices.getMealsQuery(query)

    suspend fun getMealDetail(idMeal : String) =  apiServices.getMealDetail(idMeal)
}