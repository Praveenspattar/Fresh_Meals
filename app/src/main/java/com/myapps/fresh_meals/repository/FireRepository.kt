package com.myapps.fresh_meals.repository

import com.myapps.fresh_meals.Api.AuthServices
import com.myapps.fresh_meals.Utils.MyAuthResult

class FireRepository(private val authServices : AuthServices) {
    suspend fun createAccount(email: String, password: String): MyAuthResult {
        return authServices.createAccount(email, password)
    }
}