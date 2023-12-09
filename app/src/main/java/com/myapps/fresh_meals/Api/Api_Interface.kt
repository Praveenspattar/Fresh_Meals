package com.myapps.fresh_meals.Api

import com.myapps.fresh_meals.model.MealCategory.MealCategories
import com.myapps.fresh_meals.model.Meals_data
import com.myapps.fresh_meals.model.QueryResponse.QueryResponse
import com.myapps.fresh_meals.model.SingleMeal.Meal
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api_Interface {

    @GET("random.php")
    suspend fun getMealsData(): Response<Meals_data>

    @GET("categories.php")
    suspend fun getCategoryData(): Response<MealCategories>

    @GET("filter.php")
    suspend fun getMealsQuery(@Query("c") category: String): Response<QueryResponse>

    @GET("lookup.php")
    suspend fun getMealDetail(@Query("i") mealId : String) :Response<Meal>

}