package com.myapps.fresh_meals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.myapps.fresh_meals.Api.Api_Interface
import com.myapps.fresh_meals.Utils.constants
import com.myapps.fresh_meals.repository.MealsRepository
import com.myapps.fresh_meals.viewModel.mealsViewModel
import com.myapps.fresh_meals.viewModel.viewModelFactory

class MainActivity : AppCompatActivity() {
//    lateinit var response: Meals_data
    lateinit var viewModel: mealsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomnav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        val navController = findNavController(R.id.fragment)
        NavigationUI.setupWithNavController(bottomnav,navController)

        val api by lazy { constants.getRetrofitInstant().create(Api_Interface::class.java) }
        val repo = MealsRepository(api)
        val viewModelFactory = viewModelFactory(repo)
        viewModel = ViewModelProvider(this,viewModelFactory).get(mealsViewModel::class.java)

        viewModel.liveData.observe(this,{praveen->
//            if (praveen != null)
                Log.d("the response","onCreate ${praveen}")
        })

    }
}