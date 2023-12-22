package com.myapps.fresh_meals.repository

import com.myapps.fresh_meals.Api.FireServices
import com.myapps.fresh_meals.Utils.MyAuthResult

class FireRepository(private val fireServices : FireServices) {
    suspend fun createAccount(email: String, password: String): MyAuthResult {
        return fireServices.createAccount(email, password)
    }

    suspend fun signIn(email: String, password: String): MyAuthResult{
        return fireServices.signIn(email, password)
    }
}