package com.myapps.fresh_meals

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import androidx.lifecycle.ViewModelProvider
import com.myapps.fresh_meals.Api.Api_Interface
import com.myapps.fresh_meals.Utils.constants
import com.myapps.fresh_meals.databinding.ActivityFullScreenBinding
import com.myapps.fresh_meals.repository.MealsRepository
import com.myapps.fresh_meals.viewModel.MealsViewModel
import com.myapps.fresh_meals.viewModel.viewModelFactory

class FullScreenActivity : AppCompatActivity() {

    lateinit var binding : ActivityFullScreenBinding
    lateinit var viewModel : MealsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val api by lazy { constants.getRetrofitInstant().create(Api_Interface::class.java) }
        val repo = MealsRepository(api)
        val factory = viewModelFactory(repo)
        viewModel = ViewModelProvider(this,factory)[MealsViewModel::class.java]

        val intent = intent.getStringExtra("meal")
        val mealId = intent.toString()

        if (mealId != "") {
            viewModel.getMealDetail(mealId)
            Log.d("mealidis ", "id $mealId")
        } else {
            Log.d("meal id is empty ", "meal id  $mealId")
        }

        viewModel.liveDataMeal.observe(this){
            Log.d(" thedata"," data ${it.meals[0]}")
            binding.tvMealName.text = it.meals[0].strMeal
            binding.tvInstruction.text = it.meals[0].strInstructions

            binding.videoView.setVideoURI(Uri.parse(it.meals[0].strYoutube))

//            binding.videoView.settings.javaScriptEnabled = true
//            binding.videoView.settings.pluginState = WebSettings.PluginState.ON
//            binding.videoView.loadUrl(it.meals[0].strYoutube)
//            binding.videoView.webChromeClient = WebChromeClient()
        }

    }
}