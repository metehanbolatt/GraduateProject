package com.metehanbolat.graduateproject.models.allfoodmodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BringAllFoodModel (
    @SerializedName("yemekler")
    @Expose
    var foods : List<FoodModel>,
    @SerializedName("success")
    @Expose
    var success : Int ) : Serializable