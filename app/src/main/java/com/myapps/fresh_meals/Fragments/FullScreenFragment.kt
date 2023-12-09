package com.myapps.fresh_meals.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import androidx.lifecycle.ViewModelProvider
import com.myapps.fresh_meals.Api.Api_Interface
import com.myapps.fresh_meals.R
import com.myapps.fresh_meals.Utils.constants
import com.myapps.fresh_meals.databinding.FragmentFullScreenBinding
import com.myapps.fresh_meals.repository.MealsRepository
import com.myapps.fresh_meals.viewModel.MealsViewModel
import com.myapps.fresh_meals.viewModel.viewModelFactory
import java.lang.Integer.parseInt

// TODO: Rename parameter arguments, choose names that match

class FullScreenFragment : Fragment() {

    lateinit var viewModel : MealsViewModel
    lateinit var binding :FragmentFullScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentFullScreenBinding.inflate(layoutInflater, container , false)

        val api by lazy { constants.getRetrofitInstant().create(Api_Interface::class.java) }
        val repo = MealsRepository(api)
        val factory = viewModelFactory(repo)
        viewModel = ViewModelProvider(this,factory)[MealsViewModel::class.java]

        //val intent :Intent = Intent.getIntentOld("meal")
        //val mealId : Int = Integer.parseInt(intent.toString())

        //val mealId = Integer.parseInt(arguments.toString())
        val mealId = arguments?.getString("meal")

        if (mealId != null) {
            viewModel.getMealDetail(mealId)
        } else {
            Log.d("meal id is empty ", "meal id  $mealId")
        }

        viewModel.liveDataMeal.observe(viewLifecycleOwner){
            Log.d(" thedata"," data ${it}")
            binding.tvMealName.text = it.strMeal
            binding.tvInstruction.text = it.strInstructions

            binding.webView.settings.javaScriptEnabled = true
            binding.webView.settings.pluginState = WebSettings.PluginState.ON
            binding.webView.loadUrl(it.strYoutube)
            binding.webView.webChromeClient = WebChromeClient()
        }

        return binding.root
    }

}