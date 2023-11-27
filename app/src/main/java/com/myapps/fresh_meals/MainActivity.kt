package com.myapps.fresh_meals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var response: Meals_data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(Api_Interface::class.java)

        lifecycleScope.launch(Dispatchers.IO) {
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
        }
    }
}