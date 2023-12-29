package com.myapps.fresh_meals.Api

import com.myapps.fresh_meals.model.Firebase.FavouriteData

interface FirebaseDataSource {

    /***to fetch data*/
    suspend fun getData()
}