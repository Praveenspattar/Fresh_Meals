package com.myapps.fresh_meals.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.myapps.fresh_meals.Api.FirebaseDataSource
import com.myapps.fresh_meals.model.Firebase.FavouriteData
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FireDataRepository :FirebaseDataSource {
    /***to fetch data*/

    private val userUID = FirebaseAuth.getInstance().currentUser?.uid.toString()
    private val _data = MutableLiveData<List<FavouriteData>>()
    val data: LiveData<List<FavouriteData>> get() = _data
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference.child(userUID).child("fav")


    override suspend fun getData() {
        databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val dataList = mutableListOf<FavouriteData>()
                    for (myMeal in snapshot.children) {
                        val meal = myMeal.getValue(FavouriteData::class.java)
                        meal?.let { dataList.add(it) }
                    }
                    _data.postValue(dataList)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle the error as needed
                }
            })
    }
}
