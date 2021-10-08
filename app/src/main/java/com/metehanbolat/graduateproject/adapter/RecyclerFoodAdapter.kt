package com.metehanbolat.graduateproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.metehanbolat.graduateproject.R
import com.metehanbolat.graduateproject.databinding.FoodRecyclerLayoutBinding
import com.metehanbolat.graduateproject.models.allfoodmodel.FoodModel
import com.metehanbolat.graduateproject.viewmodel.FoodsFragmentViewModel
import com.squareup.picasso.Picasso

class RecyclerFoodAdapter(var context : Context, private var foodList : List<FoodModel>, var viewModel : FoodsFragmentViewModel, var auth: FirebaseAuth) : RecyclerView.Adapter<RecyclerFoodAdapter.FoodViewHolder>() {

    private var counter = 0

    class FoodViewHolder(var binding : FoodRecyclerLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = FoodRecyclerLayoutBinding.inflate(layoutInflater, parent, false)

        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foodList[position]
        val f = holder.binding
        f.foodObject = food

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${food.food_image}"
        Picasso.get().load(url).into(f.recyclerFoodImage)

        if (counter < foodList.size){
            val animation = AnimationUtils.loadAnimation(context, R.anim.zoom_in_recycler)
            f.linearCard.startAnimation(animation)
            counter++
        }

        f.linearCard.setOnClickListener {
            viewModel.moveFoodDetails(it, food.food_name, food.food_price, url, food.food_id, food.food_image)
        }

    }

    override fun getItemCount(): Int {
        return foodList.size
    }

}