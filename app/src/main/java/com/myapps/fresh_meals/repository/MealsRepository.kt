package com.myapps.fresh_meals.repository

import com.myapps.fresh_meals.Api.Api_Interface
import com.myapps.fresh_meals.Utils.constants

class MealsRepository(val apiServices : Api_Interface) {

//    suspend fun getmealData() = constants.getRetrofitInstant().create(apiServices::class.java)
    suspend fun getmealData() = apiServices.getMealsData()
}