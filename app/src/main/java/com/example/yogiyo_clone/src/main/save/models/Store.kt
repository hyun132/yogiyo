package com.example.yogiyo_clone.src.main.save.models


import com.google.gson.annotations.SerializedName

data class Store(
    val countOwnerComment: Int,
    val countReview: Int,
    val discountCharge: Int,
    val icon: String,
    val menus: String,
    val rateAvg: Double,
    val storeIdx: Int,
    val title: String
)