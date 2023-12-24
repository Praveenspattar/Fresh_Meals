package com.myapps.fresh_meals.Api

import com.myapps.fresh_meals.model.Firebase.FavouriteData

/**to Upload data from firebase*/
interface DatabaseService {
    suspend fun uploadFavouriteData(favouriteData: FavouriteData): Result<Unit>

}