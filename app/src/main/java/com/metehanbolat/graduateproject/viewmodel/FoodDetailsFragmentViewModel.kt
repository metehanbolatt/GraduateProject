package com.metehanbolat.graduateproject.viewmodel

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.airbnb.lottie.LottieAnimationView
import com.metehanbolat.graduateproject.R
import com.metehanbolat.graduateproject.repo.FoodDaoRepository
import com.squareup.picasso.Picasso

class FoodDetailsFragmentViewModel : ViewModel() {

    private val fdaor = FoodDaoRepository()

    fun addFoodInBasket(food_id: Int, food_name : String, food_image_name : String, food_price : Int, food_order_total : String, user_name : String){
        fdaor.addFoodInBasket(food_id, food_name, food_image_name, food_price, food_order_total, user_name)
    }

    fun getFoodDetails(context: Context, foodName : String): String {
        var foodInfo = ""
        when(foodName){
            context.resources.getString(R.string.ayran) -> { foodInfo = context.resources.getString(R.string.ayranInfo) }
            context.resources.getString(R.string.baklava) -> { foodInfo = context.resources.getString(R.string.baklavaInfo) }
            context.resources.getString(R.string.fanta) -> { foodInfo = context.resources.getString(R.string.fantaInfo) }
            context.resources.getString(R.string.izgara_somon) -> { foodInfo = context.resources.getString(R.string.izgara_somonInfo) }
            context.resources.getString(R.string.izgara_tavuk) -> { foodInfo = context.resources.getString(R.string.izgara_tavukInfo) }
            context.resources.getString(R.string.kadayif) -> { foodInfo = context.resources.getString(R.string.kadayifInfo) }
            context.resources.getString(R.string.kahve) -> { foodInfo = context.resources.getString(R.string.kahveInfo) }
            context.resources.getString(R.string.kofte) -> { foodInfo = context.resources.getString(R.string.kofteInfo) }
            context.resources.getString(R.string.lazanya) -> { foodInfo = context.resources.getString(R.string.lazanyaInfo) }
            context.resources.getString(R.string.makarna) -> { foodInfo = context.resources.getString(R.string.makarnaInfo) }
            context.resources.getString(R.string.pizza) -> { foodInfo = context.resources.getString(R.string.pizzaInfo) }
            context.resources.getString(R.string.su) -> { foodInfo = context.resources.getString(R.string.suInfo) }
            context.resources.getString(R.string.sutlac) -> { foodInfo = context.resources.getString(R.string.sutlacInfo) }
            context.resources.getString(R.string.tiramisu) -> { foodInfo = context.resources.getString(R.string.tiramisuInfo) }
        }
        return foodInfo
    }

    fun decreaseOrIncrease(context: Context, orderAmount : Int, foodPrice : Int, increaseDecrease : Int) : Pair<String, String>{
        val amount : Int = orderAmount
        val newAmount : Int = amount + increaseDecrease
        val orderAmountText = newAmount.toString()
        val foodPriceText = context.resources.getString(R.string.foodMoney, (foodPrice * newAmount).toString())

        return Pair(orderAmountText, foodPriceText)
    }

    fun moveFoodsFragment(view : View){
        Navigation.findNavController(view).navigate(R.id.action_foodDetailsFragment_to_foodsFragment)
    }

    fun loadFoodImage(url : String, image : ImageView){
        Picasso.get().load(url).into(image)
    }

    fun imageAnimateFirst(context : Context, image : ImageView){
        val animation = AnimationUtils.loadAnimation(context, R.anim.zoom_in)
        image.animation = animation
    }

    fun imageAnimate(context : Context, image : ImageView){
        image.visibility = View.VISIBLE
        val animation = AnimationUtils.loadAnimation(context, R.anim.zoom_in_food)
        image.animation = animation
    }

    fun imageAnimateZoomOut(image: View){
        image.visibility = View.INVISIBLE
    }

    fun cartAnimate(context: Context, image : View){
        val animation = AnimationUtils.loadAnimation(context, R.anim.slide_right)
        image.startAnimation(animation)
    }

    fun cartAnimateBack(context: Context, image: View){
        val animation = AnimationUtils.loadAnimation(context, R.anim.slide_left)
        image.startAnimation(animation)
    }

    fun lottieAnimation(animation : LottieAnimationView){
        animation.speed = 1.5f
        animation.playAnimation()
    }

    fun backPressed(activity : FragmentActivity, view : View){
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                moveFoodsFragment(view)
            } }
        activity.onBackPressedDispatcher.addCallback(callback)
    }

}