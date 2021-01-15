package com.example.yogiyo_clone.src.main.home.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Store(
    val countLike: Int,
    val countReview: Int,
    val deliveryCharge: Int,
    val deliveryTime: String,
    val discountCharge: Int,
    val isExpress: String,
    val menus: String?,
    val rateAvg: Float?,
    val src: String?,
    val storeIdx: Int,
    val title: String
):Serializable