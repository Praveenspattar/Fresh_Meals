package com.myapps.fresh_meals.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myapps.fresh_meals.Adapter.FavouriteDataAdapter
import com.myapps.fresh_meals.databinding.FragmentFavouritesBinding
import com.myapps.fresh_meals.repository.FetchDataRepo
import com.myapps.fresh_meals.viewModel.FetchDataViewModel
import com.myapps.fresh_meals.viewModel.FetchModelFactory

/**
 * A simple [Fragment] subclass.
 * Use the [FavouritesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavouritesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var binding: FragmentFavouritesBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel : FetchDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouritesBinding.inflate(layoutInflater, container, false)

        recyclerView = binding.favRv
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        var repo = FetchDataRepo()
        var modelFactory=  FetchModelFactory(repo)
        viewModel = ViewModelProvider(this,modelFactory)[FetchDataViewModel::class.java]

        Log.d("iwant: ", "above scope")
        viewModel.fetchData()
        viewModel.myData.observe(viewLifecycleOwner) { data ->
            if (data != null && data.isNotEmpty()) {
                println("iwant: $data")
                recyclerView.adapter = FavouriteDataAdapter(data)
            } else {
                Log.d("iwant: ", "No data available")
            }
        }

        return binding.root
    }
}