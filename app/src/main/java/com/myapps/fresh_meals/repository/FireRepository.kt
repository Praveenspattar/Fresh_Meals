package com.myapps.fresh_meals.repository

import com.myapps.fresh_meals.Api.FirebaseInterface

class FireRepository(val apiServices : FirebaseInterface) {
    suspend fun getFireData() = apiServices.getFbData()
}