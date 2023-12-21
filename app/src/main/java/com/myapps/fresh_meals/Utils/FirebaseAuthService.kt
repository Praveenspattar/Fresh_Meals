package com.myapps.fresh_meals.Utils

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.myapps.fresh_meals.Api.AuthServices
import kotlinx.coroutines.tasks.await

class FirebaseAuthService : AuthServices {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override suspend fun createAccount(email: String, password: String): MyAuthResult {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            MyAuthResult.Success(result.user!!)
        } catch (e: Exception) {
            MyAuthResult.Error(e)
        }
    }
}