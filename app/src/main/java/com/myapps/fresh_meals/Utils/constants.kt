package com.myapps.fresh_meals.Utils

import com.myapps.fresh_meals.Api.Api_Interface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object constants {
    const val BaseURL = "https://www.themealdb.com/api/json/v1/1/"
    fun getRetrofitInstant() :Retrofit{
        return Retrofit.Builder().baseUrl(BaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

//    val api by lazy {
//        getRetrofitInstant().create(Api_Interface::class.java)
//    }

}