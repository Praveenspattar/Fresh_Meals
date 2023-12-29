package com.myapps.fresh_meals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.myapps.fresh_meals.Utils.FirebaseAuthService
import com.myapps.fresh_meals.Utils.MyAuthResult
import com.myapps.fresh_meals.databinding.ActivityCreateAccBinding
import com.myapps.fresh_meals.repository.FireRepository
import com.myapps.fresh_meals.viewModel.FireModelFactory
import com.myapps.fresh_meals.viewModel.FireViewModel

class CreateAccActivity : AppCompatActivity() {

    lateinit var viewModel: FireViewModel
    lateinit var binding : ActivityCreateAccBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = FirebaseAuth.getInstance().currentUser

        Thread.sleep(2000)
        installSplashScreen()
        if (user != null){
            println("helllo")
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding = ActivityCreateAccBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val fireViewModel = FireModelFactory(binding.emailAddressEditText.toString(),binding.passwordEditText.toString())
//        viewModel = ViewModelProvider(this,fireViewModel)[FireViewModel::class.java]
//       // viewModel = FireViewModel(binding.emailAddressEditText.toString(),binding.passwordEditText.toString())
//
//        binding.signInBtn.setOnClickListener({
//            println("emil  "+ binding.emailAddressEditText.text.toString().trim())
//            viewModel.createUser(binding.emailAddressEditText.text.toString().trim(),binding.passwordEditText.toString().trim(),this)
//        })

        ///
        val authService = FirebaseAuthService(this) // You need to implement this class
        val authRepository = FireRepository(authService)
        viewModel = ViewModelProvider(this, FireModelFactory(authRepository))[FireViewModel::class.java]

        viewModel.createAccountResult.observe(this, Observer { result ->
            when (result) {
                is MyAuthResult.Success -> {
                    // Account creation successful, handle accordingly
                    val user = result.user
                    Toast.makeText(this, "Account created successfully for ${user.email}", Toast.LENGTH_SHORT).show()
                }
                is MyAuthResult.Error -> {
                    // Account creation failed, handle accordingly
                    Toast.makeText(this, "Account creation failed: ${result.exception.message}", Toast.LENGTH_SHORT).show()
                }
            }
        })

        //  Trigger account creation on button click
        binding.signUpBtn.setOnClickListener {
            val email = binding.emailAddressEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            viewModel.createAccount(email, password)
        }

    }
}