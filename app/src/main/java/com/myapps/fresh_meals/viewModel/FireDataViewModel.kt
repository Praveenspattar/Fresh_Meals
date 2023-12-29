package com.myapps.fresh_meals.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.myapps.fresh_meals.model.Firebase.FavouriteData
import com.myapps.fresh_meals.repository.FireDataRepository
import kotlinx.coroutines.launch

class FireDataViewModel(private val repository: FireDataRepository) : ViewModel() {
    /***to fetch data*/

    init {
        fetchData()
    }

    lateinit var iwant:LiveData<List<FavouriteData>>

    fun fetchData() = viewModelScope.launch {
        iwant= repository.data
        //println("$$$$$$$ "+iwant)
    }

}