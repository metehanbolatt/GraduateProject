package com.metehanbolat.graduateproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.metehanbolat.graduateproject.R
import com.metehanbolat.graduateproject.databinding.FoodBasketRecyclerLayoutBinding
import com.metehanbolat.graduateproject.models.basketfoodmodel.BasketFoodModel
import com.metehanbolat.graduateproject.viewmodel.FoodBasketFragmentViewModel
import com.squareup.picasso.Picasso

class RecyclerBasketFoodAdapter(var context : Context, private var foodBasketList : List<BasketFoodModel>, var viewModel : FoodBasketFragmentViewModel) : RecyclerView.Adapter<RecyclerBasketFoodAdapter.FoodBasketViewHolder>() {

    class FoodBasketViewHolder(var binding : FoodBasketRecyclerLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodBasketViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = FoodBasketRecyclerLayoutBinding.inflate(layoutInflater, parent, false)

        return FoodBasketViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodBasketViewHolder, position: Int) {
        val foodBasket = foodBasketList[position]
        val fb = holder.binding
        fb.foodBasketObject = foodBasket

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${foodBasket.food_image}"
        Picasso.get().load(url).into(fb.recyclerBasketFoodImage)

        fb.recyclerOrderDeleteImage.setOnClickListener { clickView ->
            Snackbar.make(clickView, context.resources.getString(R.string.out_of_basket, foodBasket.food_name), Snackbar.LENGTH_INDEFINITE).setAction(context.resources.getString(R.string.remove)){
                viewModel.delete(clickView, foodBasket.food_id)
            }.show()
        }

    }

    override fun getItemCount(): Int {
        return foodBasketList.size
    }

}