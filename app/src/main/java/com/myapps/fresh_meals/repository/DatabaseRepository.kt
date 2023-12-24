package com.myapps.fresh_meals.repository

import com.myapps.fresh_meals.Api.DatabaseService
import com.myapps.fresh_meals.model.Firebase.FavouriteData

/** to upload data*/
class DatabaseRepository(private val databaseService: DatabaseService) {

    suspend fun uploadFavouriteData(favouriteData: FavouriteData): Result<Unit> {
        return databaseService.uploadFavouriteData(favouriteData)
    }
}