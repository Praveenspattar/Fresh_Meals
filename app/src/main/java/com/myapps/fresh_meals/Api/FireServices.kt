package com.myapps.fresh_meals.Api

import com.myapps.fresh_meals.Utils.MyAuthResult

interface FireServices {

    suspend fun createAccount(email: String, password: String) : MyAuthResult

    suspend fun signIn(email: String, password: String): MyAuthResult

}