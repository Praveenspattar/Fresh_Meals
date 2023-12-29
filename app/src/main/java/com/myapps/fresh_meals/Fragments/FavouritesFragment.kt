package com.myapps.fresh_meals.Fragments

import android.content.Intent
import android.icu.lang.UCharacter.VerticalOrientation
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myapps.fresh_meals.Adapter.FavouriteDataAdapter
import com.myapps.fresh_meals.R
import com.myapps.fresh_meals.Utils.FirebaseDatabaseService
import com.myapps.fresh_meals.databinding.FragmentFavouritesBinding
import com.myapps.fresh_meals.repository.DatabaseRepository
import com.myapps.fresh_meals.repository.FireDataRepository
import com.myapps.fresh_meals.viewModel.DatabaseViewModel
import com.myapps.fresh_meals.viewModel.DatabaseViewModelFactory
import com.myapps.fresh_meals.viewModel.FireDataModelFactory
import com.myapps.fresh_meals.viewModel.FireDataViewModel
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [FavouritesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavouritesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var binding: FragmentFavouritesBinding
    lateinit var viewModel: FireDataViewModel
    private lateinit var recyclerView: RecyclerView
    //private lateinit var favouriteDataAdapter: FavouriteDataAdapter // Add this line

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouritesBinding.inflate(layoutInflater, container, false)

        val repository = FireDataRepository()
        val viewModelFactory = FireDataModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[FireDataViewModel::class.java]

        recyclerView = binding.favRv
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        //favouriteDataAdapter = FavouriteDataAdapter(emptyList())
        //recyclerView.adapter = favouriteDataAdapter

        println("iwant  ")
        viewModel.iwant.observe(viewLifecycleOwner){
            val response = it
            println("iwant: $it")
            recyclerView.adapter = FavouriteDataAdapter(response)
        }
        return binding.root
    }
}