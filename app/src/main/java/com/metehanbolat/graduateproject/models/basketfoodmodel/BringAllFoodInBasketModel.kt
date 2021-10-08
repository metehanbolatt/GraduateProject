package com.metehanbolat.graduateproject.models.basketfoodmodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BringAllFoodInBasketModel (
    @SerializedName("sepet_yemekler")
    @Expose
    var foods : List<BasketFoodModel>,
    @SerializedName("success")
    @Expose
    var success : Int) : Serializable