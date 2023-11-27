package com.myapps.fresh_meals

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api_Interface {

    @GET("random.php")
    suspend fun getMealsData(): Response<Meals_data>

}