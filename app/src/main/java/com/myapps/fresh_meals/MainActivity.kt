package com.myapps.fresh_meals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
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

       /* val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(Api_Interface::class.java)*/

        /*lifecycleScope.launch(Dispatchers.IO) {
            try {
                val apiResponse = retrofit.getMealsData()

                if (apiResponse.isSuccessful) {
                    response = apiResponse.body()!!

                    // Perform actions dependent on the response here
                    Log.d("praveen", "onCreate $response")
                } else {
                    Log.e("its the error", "Error: ${apiResponse.code()}")
                }
            } catch (e: Exception) {
                Log.e("its the error", "${e.message}")
            }
        }*/

        //val repository = MealsRepository()
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