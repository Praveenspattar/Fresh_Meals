package com.myapps.fresh_meals.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.myapps.fresh_meals.model.Firebase.FavouriteData
import kotlinx.coroutines.tasks.await

class FetchDataRepo {
    private val userUID = FirebaseAuth.getInstance().currentUser?.uid.toString()
    private val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().reference.child(userUID).child("fav")

    suspend fun getData(): List<FavouriteData> {
        return try {
            println("iwant repo")
            val dataSnapshot = databaseReference.get().await()
            val dataList = mutableListOf<FavouriteData>()

            dataSnapshot.children.forEach { data ->
                val favouriteData = data.getValue(FavouriteData::class.java)
                favouriteData?.let { dataList.add(it) }
            }

            dataList
        } catch (e: Exception) {
            // Handle the exception (e.g., log it or throw a custom exception)
            emptyList()
        }
    }
}