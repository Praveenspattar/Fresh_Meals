package com.myapps.fresh_meals.Utils

import com.google.firebase.auth.FirebaseAuth
import com.myapps.fresh_meals.Api.FireServices
import kotlinx.coroutines.tasks.await

class FirebaseAuthService : FireServices {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override suspend fun createAccount(email: String, password: String): MyAuthResult {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            MyAuthResult.Success(result.user!!)
        } catch (e: Exception) {
            MyAuthResult.Error(e)
        }
    }

    override suspend fun signIn(email: String, password: String): MyAuthResult {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            MyAuthResult.Success(result.user!!)
        } catch (e: Exception) {
            MyAuthResult.Error(e)
        }
    }
}