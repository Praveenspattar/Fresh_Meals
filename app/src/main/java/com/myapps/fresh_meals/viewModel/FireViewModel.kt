package com.myapps.fresh_meals.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.myapps.fresh_meals.Utils.MyAuthResult
import com.myapps.fresh_meals.repository.FireRepository
import kotlinx.coroutines.launch

class FireViewModel(private val fireRepository: FireRepository) : ViewModel() {


    ///
    private val _createAccountResult = MutableLiveData<MyAuthResult>()
    private val _signInResult = MutableLiveData<MyAuthResult>()

    val createAccountResult: LiveData<MyAuthResult> get() = _createAccountResult
    val signInResult: LiveData<MyAuthResult> get() = _signInResult

    fun createAccount(email: String, password: String) {
        viewModelScope.launch {
            _createAccountResult.value = fireRepository.createAccount(email, password)
        }
    }

    fun signIn(email: String,password: String) {
        viewModelScope.launch {
            _signInResult.value = fireRepository.signIn(email, password)
        }
    }

}