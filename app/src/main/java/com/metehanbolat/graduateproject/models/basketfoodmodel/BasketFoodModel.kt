package com.metehanbolat.graduateproject.models.basketfoodmodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BasketFoodModel (
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
    var food_price : String,
    @SerializedName("yemek_siparis_adet")
    @Expose
    var food_order : Int,
    @SerializedName("kullanici_adi")
    @Expose
    var user_name : String ) : Serializable