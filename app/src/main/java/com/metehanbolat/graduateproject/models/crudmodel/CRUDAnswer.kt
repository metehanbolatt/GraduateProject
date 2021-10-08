package com.metehanbolat.graduateproject.models.crudmodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CRUDAnswer(
    @SerializedName("success")
    @Expose
    var success : Int,
    @SerializedName("message")
    @Expose
    var message : String)