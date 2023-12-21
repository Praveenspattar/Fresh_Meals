package com.myapps.fresh_meals.Api

import com.google.firebase.auth.AuthResult
import com.myapps.fresh_meals.Utils.MyAuthResult

interface AuthServices {

    suspend fun createAccount(email: String, password: String) : MyAuthResult

}