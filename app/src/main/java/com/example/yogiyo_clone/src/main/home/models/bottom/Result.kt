package com.example.yogiyo_clone.src.main.home.models.bottom


import com.google.gson.annotations.SerializedName

data class Result(
    val countReview: Int?,
    val deliveryCharge: Int?,
    val deliveryTime: String?,
    val isExpress: String?,
    val poster: String?,
    val rateAvg: Float?,
    val storeIdx: Int?,
    val title: String?
)