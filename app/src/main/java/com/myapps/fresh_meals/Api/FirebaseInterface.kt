package com.myapps.fresh_meals.Api

import com.myapps.fresh_meals.model.Firebase.Favourite
import retrofit2.Response
import retrofit2.http.GET

interface FirebaseInterface {
    @GET()
    suspend fun getFbData():Response<Favourite>
}