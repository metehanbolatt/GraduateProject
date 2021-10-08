package com.metehanbolat.graduateproject.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.metehanbolat.graduateproject.R
import com.metehanbolat.graduateproject.adapter.RecyclerBasketFoodAdapter
import com.metehanbolat.graduateproject.databinding.FragmentFoodBasketBinding
import com.metehanbolat.graduateproject.models.basketfoodmodel.BasketFoodModel
import com.metehanbolat.graduateproject.viewmodel.FoodBasketFragmentViewModel

class FoodBasketFragment : Fragment() {

    private lateinit var binding : FragmentFoodBasketBinding
    private lateinit var viewModel : FoodBasketFragmentViewModel
    private lateinit var adapter : RecyclerBasketFoodAdapter
    private lateinit var finalBasketList : ArrayList<BasketFoodModel>
    private lateinit var intList: ArrayList<Int>
    private lateinit var intListTwo: ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : FoodBasketFragmentViewModel by viewModels()
        viewModel = tempViewModel
        finalBasketList = ArrayList()
        intList = ArrayList()
        intListTwo = ArrayList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food_basket, container, false)
        binding.foodBasketFragment = this

        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                viewModel.moveFoodsFragment(binding.root)
            } }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
        intList.add(99)
        viewModel.foodBasketList.observe(viewLifecycleOwner,{ foodBasketList ->
            for (x in foodBasketList.indices) {
                var control = 0
                intList.forEach {
                    if (foodBasketList[x].food_id == it){
                        control = 1
                    }
                }
                if (control != 1){
                    intListTwo.add(foodBasketList[x].food_order)
                    for (y in x + 1 until foodBasketList.size) {
                        if (foodBasketList[x].food_id == foodBasketList[y].food_id){
                            intListTwo.add(foodBasketList[y].food_order)
                        }
                    }
                    var sum = 0
                    intListTwo.forEach { sumIt ->
                        sum += sumIt
                    }
                    val newFood = BasketFoodModel(
                        food_id = foodBasketList[x].food_id,
                        food_name = foodBasketList[x].food_name,
                        food_image = foodBasketList[x].food_image,
                        food_price = (((foodBasketList[x].food_price.toInt() / foodBasketList[x].food_order) * sum).toString()),
                        food_order = sum,
                        user_name = foodBasketList[x].user_name)
                    finalBasketList.add(newFood)
                    intListTwo.clear()
                }
                intList.add(foodBasketList[x].food_id)
            }
            //adapter = RecyclerBasketFoodAdapter(requireContext(), foodBasketList, viewModel)
            adapter = RecyclerBasketFoodAdapter(requireContext(), finalBasketList, viewModel)
            binding.basketFoodAdapter = adapter
        })

        return binding.root
    }

    fun moveFoodsFragment(v : View){
        viewModel.moveFoodsFragment(v)
    }

}