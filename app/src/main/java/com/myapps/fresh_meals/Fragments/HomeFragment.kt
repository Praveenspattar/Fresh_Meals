package com.myapps.fresh_meals.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myapps.fresh_meals.Adapter.CategoryAdapter
import com.myapps.fresh_meals.Api.Api_Interface
import com.myapps.fresh_meals.Utils.constants
import com.myapps.fresh_meals.databinding.FragmentHomeBinding
import com.myapps.fresh_meals.repository.MealsRepository
import com.myapps.fresh_meals.viewModel.MealsViewModel
import com.myapps.fresh_meals.viewModel.viewModelFactory

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: MealsViewModel
    private lateinit var recyclerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        val api by lazy { constants.getRetrofitInstant().create(Api_Interface::class.java) }
        val repo = MealsRepository(api)
        val viewModelFactory = viewModelFactory(repo)
        viewModel = ViewModelProvider(this, viewModelFactory)[MealsViewModel::class.java]

        recyclerView = binding.recyclerView
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.liveDataCategory.observe(viewLifecycleOwner) {
            val response = it.categories
            recyclerView.adapter = CategoryAdapter(response)
        }
        return binding.root

    }
}