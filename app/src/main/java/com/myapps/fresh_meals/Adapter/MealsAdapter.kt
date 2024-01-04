package com.myapps.fresh_meals.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myapps.fresh_meals.FullScreenActivity
import com.myapps.fresh_meals.R
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

        holder.itemView.setBackgroundResource(R.drawable.backgrd1)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,FullScreenActivity::class.java)
            intent.putExtra("meal",list[position].idMeal)
            holder.itemView.context.startActivity(intent)

//            val bundle = Bundle()
//            bundle.putString("meal",list[position].idMeal)

//            val bundle = Bundle()
//            bundle.putString("meal",list[position].idMeal)
//
//            val frg =  FullScreenFragment()
//            frg.arguments = bundle
//
//            val transaction = (holder.itemView.context as AppCompatActivity).supportFragmentManager.beginTransaction()
//            transaction.add(R.id.fragmentContainer, frg) // Replace with your fragment container ID
//            transaction.addToBackStack(null) // Optional: add to back stack
//            transaction.commit()

        }

    }

    class MealViewHolder(var binding: MealsItemBinding) : RecyclerView.ViewHolder(binding.root)

}