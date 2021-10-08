package com.metehanbolat.graduateproject.retrofit

class ApiUtils {

    companion object {
        private const val BASE_URL = "http://kasimadalan.pe.hu/"

        fun bringAllFood() : FoodDaoInterface {
            return RetrofitClient.getClient(BASE_URL).create(FoodDaoInterface::class.java)
        }
    }
}