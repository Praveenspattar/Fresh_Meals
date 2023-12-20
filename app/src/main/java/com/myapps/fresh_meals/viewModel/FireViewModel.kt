package com.myapps.fresh_meals.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthUserCollisionException

class FireViewModel(email: String, password: String) : ViewModel() {
    val firebaseAuth = FirebaseAuth.getInstance()

    fun createUser(email: String ,password : String ,context: Context) {
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
            Toast.makeText(context ,"User Created Successfully", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener { exception ->
            // Handle authentication failures
            when (exception) {
                is FirebaseAuthUserCollisionException -> Toast.makeText(context,"User with this email already exists.",Toast.LENGTH_SHORT).show()
                is FirebaseAuthException -> Toast.makeText(context,"Authentication failed: ${exception.message}",Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(context,"User creation failed.",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun signIn(email: String,password: String) {
        firebaseAuth.signInWithEmailAndPassword(email,password)
    }

}