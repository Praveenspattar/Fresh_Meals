package com.myapps.fresh_meals.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.myapps.fresh_meals.Api.FirebaseDataSource
import com.myapps.fresh_meals.model.Firebase.FavouriteData
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FireDataRepository  :FirebaseDataSource {
    private val databaseReference = FirebaseDatabase.getInstance().getReference("https://fresh-meals-5231a-default-rtdb.firebaseio.com/")

    override suspend fun getData(): Result<List<FavouriteData>> {
        return suspendCoroutine { continuation ->
            databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val dataList = mutableListOf<FavouriteData>()
                    for (childSnapshot in snapshot.children) {
                        val data = childSnapshot.getValue(FavouriteData::class.java)
                        data?.let {
                            dataList.add(it)
                        }
                    }
                    continuation.resume(Result.success(dataList))
                }

                override fun onCancelled(error: DatabaseError) {
                    continuation.resume(Result.failure(error.toException()))
                }
            })
        }
    }
}
