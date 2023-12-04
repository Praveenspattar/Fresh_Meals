package com.myapps.fresh_meals

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.myapps.fresh_meals.Adapter.CategoryAdapter
import com.myapps.fresh_meals.Api.Api_Interface
import com.myapps.fresh_meals.Utils.constants
import com.myapps.fresh_meals.databinding.FragmentHomeBinding
import com.myapps.fresh_meals.repository.MealsRepository
import com.myapps.fresh_meals.viewModel.mealsViewModel
import com.myapps.fresh_meals.viewModel.viewModelFactory


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
//    lateinit var categoryAdapter : CategoryAdapter
    lateinit var viewModel: mealsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentHomeBinding.inflate(layoutInflater)

        val api by lazy { constants.getRetrofitInstant().create(Api_Interface::class.java) }
        val repo = MealsRepository(api)
        val viewModelFactory = viewModelFactory(repo)
        viewModel = ViewModelProvider(this,viewModelFactory)[mealsViewModel::class.java]


//        setupRecyclerview()

        val recyclerView = binding.recyclerView
        recyclerView.apply {
            layoutManager = GridLayoutManager(context,3)
        }

        viewModel.liveDataCategory.observe(this) {
//            categoryAdapter = CategoryAdapter(it.categories)
            Log.d("theCategoryData","the data ${it.categories}")
            recyclerView.adapter = CategoryAdapter(it.categories)
        }

    }

//    fun setupRecyclerview(){
//        binding.recyclerView.apply {
//            adapter = categoryAdapter
//            layoutManager = GridLayoutManager(context,3)
//        }
//    }

}