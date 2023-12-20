package com.myapps.fresh_meals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.myapps.fresh_meals.databinding.ActivityCreateAccBinding
import com.myapps.fresh_meals.viewModel.FireModelFactory
import com.myapps.fresh_meals.viewModel.FireViewModel

class CreateAccActivity : AppCompatActivity() {

    lateinit var viewModel: FireViewModel
    lateinit var binding : ActivityCreateAccBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fireViewModel = FireModelFactory(binding.emailAddressEditText.toString(),binding.passwordEditText.toString())
        viewModel = ViewModelProvider(this,fireViewModel)[FireViewModel::class.java]
       // viewModel = FireViewModel(binding.emailAddressEditText.toString(),binding.passwordEditText.toString())

        binding.signInBtn.setOnClickListener({
            println("emil  "+ binding.emailAddressEditText.text.toString().trim())
            viewModel.createUser(binding.emailAddressEditText.text.toString().trim(),binding.passwordEditText.toString().trim(),this)
        })

    }
}