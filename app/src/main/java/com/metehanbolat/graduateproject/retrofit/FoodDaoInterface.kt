package com.metehanbolat.graduateproject.retrofit

import com.metehanbolat.graduateproject.models.allfoodmodel.BringAllFoodModel
import com.metehanbolat.graduateproject.models.basketfoodmodel.BringAllFoodInBasketModel
import com.metehanbolat.graduateproject.models.crudmodel.CRUDAnswer
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodDaoInterface {
    @GET("yemekler/tumYemekleriGetir.php")
    fun allFoods() : Call<BringAllFoodModel>

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    fun deleteFood(@Field("yemek_id") food_id : Int) : Call<CRUDAnswer>

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    fun addFoodInBasket(
        @Field("yemek_id") food_id : Int,
        @Field("yemek_adi") food_name : String,
        @Field("yemek_resim_adi") food_image_name : String,
        @Field("yemek_fiyat") food_price : Int,
        @Field("yemek_siparis_adet") food_order_total : String,
        @Field("kullanici_adi") user_name : String) : Call<CRUDAnswer>

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    fun allFoodInBasket(@Field("kullanici_adi") user_name: String) : Call<BringAllFoodInBasketModel>
}