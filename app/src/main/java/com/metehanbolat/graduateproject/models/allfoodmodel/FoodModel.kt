package com.metehanbolat.graduateproject.models.allfoodmodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FoodModel (
    @SerializedName("yemek_id")
    @Expose
    var food_id : Int,
    @SerializedName("yemek_adi")
    @Expose
    var food_name : String,
    @SerializedName("yemek_resim_adi")
    @Expose
    var food_image : String,
    @SerializedName("yemek_fiyat")
    @Expose
    var food_price : Int ) : Serializable