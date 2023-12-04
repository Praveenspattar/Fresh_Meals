package com.myapps.fresh_meals.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myapps.fresh_meals.databinding.CategoryItemBinding
import com.myapps.fresh_meals.model.MealCategory.Category
import com.myapps.fresh_meals.model.MealCategory.MealCategories

class CategoryAdapter(var list: List<Category>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(CategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        Glide.with(holder.itemView).load(list[position].strCategoryThumb).into(holder.binding.imageView)
        holder.binding.textView.text = list[position].strCategory
    }

    class CategoryViewHolder(val binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }


}