package com.metehanbolat.graduateproject.viewmodel

import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentActivity
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
    private var intList: ArrayList<Int>
    private var intListTwo: ArrayList<Int>

    init {
        val uniqueEmail = DivideWords()
        loadFoodInBasket(uniqueEmail.getFirstPart(auth.currentUser!!.email.toString()))
        foodBasketList = fdaor.bringsFoodsInBasket()
        intList = ArrayList()
        intListTwo = ArrayList()
    }

    private fun loadFoodInBasket(userName : String){
        fdaor.getAllFoodsInBasket(userName)
    }

    fun delete(v : View, food_id : Int){
        fdaor.deleteFood(food_id)
        moveSelf(v)
    }

    fun moveFoodsFragment(v : View){
        Navigation.findNavController(v).navigate(R.id.action_foodBasketFragment_to_foodsFragment)
    }

    private fun moveSelf(v : View){
        Navigation.findNavController(v).navigate(R.id.action_foodBasketFragment_self)
    }

    fun specialBasketList(finalBasketList : ArrayList<BasketFoodModel>, basketList : List<BasketFoodModel>){
        for (x in basketList.indices) {
            var control = 0
            intList.forEach {
                if (basketList[x].food_id == it){
                    control = 1
                }
            }
            if (control != 1){
                intListTwo.add(basketList[x].food_order)
                for (y in x + 1 until basketList.size) {
                    if (basketList[x].food_id == basketList[y].food_id){
                        intListTwo.add(basketList[y].food_order)
                    }
                }
                var sum = 0
                intListTwo.forEach { sumIt ->
                    sum += sumIt
                }
                val newFood = BasketFoodModel(
                    food_id = basketList[x].food_id,
                    food_name = basketList[x].food_name,
                    food_image = basketList[x].food_image,
                    food_price = (((basketList[x].food_price.toInt() / basketList[x].food_order) * sum).toString()),
                    food_order = sum,
                    user_name = basketList[x].user_name)
                finalBasketList.add(newFood)
                intListTwo.clear()
            }
            intList.add(basketList[x].food_id)
        }
    }

    fun backPressed(activity : FragmentActivity, view : View){
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                moveFoodsFragment(view)
            } }
        activity.onBackPressedDispatcher.addCallback(callback)
    }

}