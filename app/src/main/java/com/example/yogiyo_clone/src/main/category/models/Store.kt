package com.example.yogiyo_clone.src.main.category.models


import com.google.gson.annotations.SerializedName

data class Store(
    val countOwnerComment: Int,
    val countReview: Int,
    val deliveryCharge: Int,
    val deliveryTime: String?,
    val discountCharge: Int,
    val icon: String?,
    val isExpress: String?,
    val isSuperRedweek: String?,
    val limitCharge: Int,
    val rateAvg: Float?,
    val storeIdx: Int,
    val title: String
)