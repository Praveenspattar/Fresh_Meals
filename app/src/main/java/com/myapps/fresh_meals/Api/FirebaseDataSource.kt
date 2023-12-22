package com.myapps.fresh_meals.Api

import com.myapps.fresh_meals.model.Firebase.FavouriteData

interface FirebaseDataSource {

    suspend fun getData() : Result<List<FavouriteData>>
}