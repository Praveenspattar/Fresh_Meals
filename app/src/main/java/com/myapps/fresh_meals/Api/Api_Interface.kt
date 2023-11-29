package com.myapps.fresh_meals.Api

import com.myapps.fresh_meals.model.Meals_data
import retrofit2.Response
import retrofit2.http.GET

interface Api_Interface {

    @GET("random.php")
    suspend fun getMealsData(): Response<Meals_data>

}