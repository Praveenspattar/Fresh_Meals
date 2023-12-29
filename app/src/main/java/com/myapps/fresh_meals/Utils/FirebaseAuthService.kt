package com.myapps.fresh_meals.Utils

import android.content.Context
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.myapps.fresh_meals.Api.FireServices
import com.myapps.fresh_meals.MainActivity
import com.myapps.fresh_meals.SignInActivity
import kotlinx.coroutines.tasks.await

class FirebaseAuthService(private val context: Context) : FireServices {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override suspend fun createAccount(email: String, password: String): MyAuthResult {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {task ->
                if (task.isSuccessful){
                    val intent = Intent(context,SignInActivity::class.java)
                    context.startActivity(intent)
                }
            }.await()
            MyAuthResult.Success(result.user!!)
        } catch (e: Exception) {
            MyAuthResult.Error(e)
        }
    }

    override suspend fun signIn(email: String, password: String): MyAuthResult {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {task ->
                    if (task.isSuccessful){
                        val intent = Intent(context,MainActivity::class.java)
                        context.startActivity(intent)
                    }
                }.await()
            MyAuthResult.Success(result.user!!)
        } catch (e: Exception) {
            MyAuthResult.Error(e)
        }
    }
}