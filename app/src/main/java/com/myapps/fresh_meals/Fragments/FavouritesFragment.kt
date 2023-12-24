package com.myapps.fresh_meals.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.myapps.fresh_meals.R
import com.myapps.fresh_meals.Utils.FirebaseDatabaseService
import com.myapps.fresh_meals.databinding.FragmentFavouritesBinding
import com.myapps.fresh_meals.repository.DatabaseRepository
import com.myapps.fresh_meals.viewModel.DatabaseViewModel
import com.myapps.fresh_meals.viewModel.DatabaseViewModelFactory

/**
 * A simple [Fragment] subclass.
 * Use the [FavouritesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavouritesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var binding: FragmentFavouritesBinding
    lateinit var viewModel: DatabaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavouritesBinding.inflate(layoutInflater, container, false)



        return binding.root
    }

}