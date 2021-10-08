package com.metehanbolat.graduateproject.repo

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.metehanbolat.graduateproject.models.allfoodmodel.BringAllFoodModel
import com.metehanbolat.graduateproject.models.crudmodel.CRUDAnswer
import com.metehanbolat.graduateproject.models.allfoodmodel.FoodModel
import com.metehanbolat.graduateproject.models.basketfoodmodel.BasketFoodModel
import com.metehanbolat.graduateproject.models.basketfoodmodel.BringAllFoodInBasketModel
import com.metehanbolat.graduateproject.mostusedfunctions.DivideWords
import com.metehanbolat.graduateproject.retrofit.ApiUtils
import com.metehanbolat.graduateproject.retrofit.FoodDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodDaoRepository {

    private val foodList : MutableLiveData<List<FoodModel>> = MutableLiveData()
    private val basketFoodList : MutableLiveData<List<BasketFoodModel>> = MutableLiveData()
    private val foodDaoInterface : FoodDaoInterface = ApiUtils.bringAllFood()
    private var auth : FirebaseAuth = Firebase.auth

    fun bringFoods() : MutableLiveData<List<FoodModel>>{
        return foodList
    }

    fun bringsFoodsInBasket() : MutableLiveData<List<BasketFoodModel>>{
        return basketFoodList
    }

    fun getAllFoods(){
        foodDaoInterface.allFoods().enqueue(object : Callback<BringAllFoodModel>{
            override fun onResponse(call: Call<BringAllFoodModel>, response: Response<BringAllFoodModel>) {
                val myFoodList = response.body()?.foods
                foodList.value = myFoodList!!
            }
            override fun onFailure(call: Call<BringAllFoodModel>, t: Throwable) {}
        })
    }

    fun deleteFood(food_id : Int){
        foodDaoInterface.deleteFood(food_id).enqueue(object : Callback<CRUDAnswer>{
            override fun onResponse(call: Call<CRUDAnswer>, response: Response<CRUDAnswer>) {
                val uniqueUser = DivideWords()
                getAllFoodsInBasket(uniqueUser.getFirstPart(auth.currentUser!!.email.toString()))
            }
            override fun onFailure(call: Call<CRUDAnswer>, t: Throwable) {}
        })
    }

    fun addFoodInBasket(food_id: Int, food_name : String, food_image_name : String, food_price : Int, food_order_total : String, user_name : String){
        foodDaoInterface.addFoodInBasket(food_id, food_name, food_image_name, food_price, food_order_total, user_name).enqueue(object : Callback<CRUDAnswer>{
            override fun onResponse(call: Call<CRUDAnswer>, response: Response<CRUDAnswer>) {}
            override fun onFailure(call: Call<CRUDAnswer>, t: Throwable) {}
        })
    }

    fun getAllFoodsInBasket(userName : String){
        foodDaoInterface.allFoodInBasket(userName).enqueue(object : Callback<BringAllFoodInBasketModel>{
            override fun onResponse(call: Call<BringAllFoodInBasketModel>, response: Response<BringAllFoodInBasketModel>) {
                val myBasketFoodList = response.body()?.foods
                basketFoodList.value = myBasketFoodList!!
            }
            override fun onFailure(call: Call<BringAllFoodInBasketModel>, t: Throwable) {}
        })
    }

}