package com.myapps.fresh_meals.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myapps.fresh_meals.databinding.ActivityMainBinding
import com.myapps.fresh_meals.databinding.MealsItemBinding
import com.myapps.fresh_meals.model.QueryResponse.Meal

class MealsAdapter(private var list : List<Meal>) : RecyclerView.Adapter<MealsAdapter.MealViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        return MealViewHolder(MealsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.binding.mealText.text = list[position].strMeal
        Glide.with(holder.itemView).load(list[position].strMealThumb).into(holder.binding.mealImage)
    }

    class MealViewHolder(var binding: MealsItemBinding) : RecyclerView.ViewHolder(binding.root)

}