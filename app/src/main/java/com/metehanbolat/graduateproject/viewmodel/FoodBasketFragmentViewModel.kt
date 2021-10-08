package com.metehanbolat.graduateproject.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.metehanbolat.graduateproject.R
import com.metehanbolat.graduateproject.models.basketfoodmodel.BasketFoodModel
import com.metehanbolat.graduateproject.mostusedfunctions.DivideWords
import com.metehanbolat.graduateproject.repo.FoodDaoRepository

class FoodBasketFragmentViewModel : ViewModel() {

    var foodBasketList = MutableLiveData<List<BasketFoodModel>>()
    private val fdaor = FoodDaoRepository()
    private var auth : FirebaseAuth = Firebase.auth

    init {
        val uniqueEmail = DivideWords()
        loadFoodInBasket(uniqueEmail.getFirstPart(auth.currentUser!!.email.toString()))
        foodBasketList = fdaor.bringsFoodsInBasket()
    }

    private fun loadFoodInBasket(userName : String){
        fdaor.getAllFoodsInBasket(userName)
    }

    fun delete(food_id : Int){
        fdaor.deleteFood(food_id)
    }

    fun moveFoodsFragment(v : View){
        Navigation.findNavController(v).navigate(R.id.action_foodBasketFragment_to_foodsFragment)
    }

}