package com.metehanbolat.graduateproject.viewmodel

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.metehanbolat.graduateproject.R
import com.metehanbolat.graduateproject.models.allfoodmodel.FoodModel
import com.metehanbolat.graduateproject.repo.FoodDaoRepository
import com.metehanbolat.graduateproject.view.activities.UserActivity
import com.metehanbolat.graduateproject.view.fragments.FoodsFragmentDirections

class FoodsFragmentViewModel : ViewModel() {

    private var auth: FirebaseAuth = Firebase.auth
    var foodList = MutableLiveData<List<FoodModel>>()
    private val fdaor = FoodDaoRepository()

    init {
        loadAllFoods()
        foodList = fdaor.bringFoods()
    }

    private fun loadAllFoods(){
        fdaor.getAllFoods()
    }

    fun exitCurrentUser(context : Context, activity : FragmentActivity){
        auth.signOut()
        val intent = Intent(context, UserActivity::class.java)
        context.startActivity(intent)
        activity.finish()
    }

    fun moveBasket(view : View){
        Navigation.findNavController(view).navigate(R.id.action_foodsFragment_to_foodBasketFragment)
    }

    fun moveFoodDetails(view : View, foodName : String, foodPrice : Int, url : String, foodId : Int, foodImage : String){
        val action = FoodsFragmentDirections.actionFoodsFragmentToFoodDetailsFragment(foodName, foodPrice, url, foodId, foodImage)
        Navigation.findNavController(view).navigate(action)
    }

}