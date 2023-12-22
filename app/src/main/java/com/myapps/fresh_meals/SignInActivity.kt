package com.myapps.fresh_meals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.myapps.fresh_meals.Utils.FirebaseAuthService
import com.myapps.fresh_meals.Utils.MyAuthResult
import com.myapps.fresh_meals.databinding.ActivitySignInBinding
import com.myapps.fresh_meals.repository.FireRepository
import com.myapps.fresh_meals.viewModel.FireModelFactory
import com.myapps.fresh_meals.viewModel.FireViewModel

class SignInActivity : AppCompatActivity() {

    lateinit var binding : ActivitySignInBinding
    lateinit var viewModel: FireViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val authService = FirebaseAuthService()
        val fireRepository = FireRepository(authService)
        viewModel = ViewModelProvider(this,FireModelFactory(fireRepository))[FireViewModel::class.java]

        viewModel.signInResult.observe(this, Observer {
            when(it){
                is MyAuthResult.Success -> {
                    val user = it.user
                    Toast.makeText(this, "SignIn successful for ${user.email}", Toast.LENGTH_SHORT).show()
                }
                is MyAuthResult.Error -> {
                    // Account creation failed, handle accordingly
                    Toast.makeText(this, "Account creation failed: ${it.exception.message}", Toast.LENGTH_SHORT).show()
                }
            }
        })

        binding.signInBtn.setOnClickListener {
            val email = binding.signinEmailEditText.text.toString()
            val password = binding.signinPasswordEditText.text.toString()
            viewModel.signIn(email, password)
        }

    }
}