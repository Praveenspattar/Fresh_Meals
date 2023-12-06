package com.myapps.fresh_meals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myapps.fresh_meals.Adapter.MealsAdapter
import com.myapps.fresh_meals.Api.Api_Interface
import com.myapps.fresh_meals.Utils.constants
import com.myapps.fresh_meals.databinding.ActivityMain2Binding
import com.myapps.fresh_meals.repository.MealsRepository
import com.myapps.fresh_meals.viewModel.MealsViewModel
import com.myapps.fresh_meals.viewModel.viewModelFactory
import retrofit2.create

class MainActivity2 : AppCompatActivity() {

    lateinit var binding: ActivityMain2Binding
    lateinit var recyclerView: RecyclerView
    lateinit var viewModel: MealsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent.getStringExtra("category")

        val api by lazy { constants.getRetrofitInstant().create(Api_Interface::class.java) }
        val repository = MealsRepository(api)
        val factory = viewModelFactory(repository)
        viewModel = ViewModelProvider(this,factory)[MealsViewModel::class.java]
        viewModel.getQueryResponse(intent.toString())

        recyclerView = binding.mealsRv
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity2)
        }

        viewModel.liveDataQuery.observe(this){
            val response = it.meals
            try {
                recyclerView.adapter = MealsAdapter(response)
            }catch (e :Exception){
                Log.e("error in viewModel Scope", e.message.toString())
            }
        }

    }

}