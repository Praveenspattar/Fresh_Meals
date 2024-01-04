package com.myapps.fresh_meals.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.myapps.fresh_meals.FavouriteViewActivity
import com.myapps.fresh_meals.FullScreenActivity
import com.myapps.fresh_meals.R
import com.myapps.fresh_meals.databinding.FavouriteSingleBinding
import com.myapps.fresh_meals.model.Firebase.FavouriteData

class FavouriteDataAdapter(private val favouriteDataList: List<FavouriteData>) :
    RecyclerView.Adapter<FavouriteDataAdapter.ViewHolder>() {
    /***to fetch data from FB*/

    class ViewHolder(val binding: FavouriteSingleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FavouriteSingleBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favouriteData = favouriteDataList[position]

        holder.binding.singleMealName.text = favouriteData.mealName

        holder.itemView.setBackgroundResource(R.drawable.backgrd1)

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context,FavouriteViewActivity::class.java)
            intent.putExtra("FavMealName",favouriteData.mealName)
            intent.putExtra("FavMealIn",favouriteData.instruction)
            intent.putExtra("FavMealYT",favouriteData.youtube)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return favouriteDataList.size
    }
}
