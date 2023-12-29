package com.myapps.fresh_meals.Utils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.myapps.fresh_meals.Api.DatabaseService
import com.myapps.fresh_meals.model.Firebase.FavouriteData
import kotlinx.coroutines.tasks.await

/** to upload data*/
class FirebaseDatabaseService : DatabaseService {

    private val userUID = FirebaseAuth.getInstance().currentUser?.uid.toString()

    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference.child(userUID).child("fav")

    override suspend fun uploadFavouriteData(favouriteData: FavouriteData): Result<Unit> {
        return try {
            val key = databaseReference.push().key
            key?.let {
                databaseReference.child(it).setValue(favouriteData).await()
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}