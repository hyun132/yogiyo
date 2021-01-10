package com.example.yogiyo_clone.src.order.menuheader.model


import com.google.gson.annotations.SerializedName

data class MenuHeaderResult(
    val countLike: Int,
    val deliveryCharge: Int,
    val deliveryTime: String,
    val description: Any,
    val discountCharge: Int,
    val isExpress: String,
    val isLike: String,
    val limitCharge: Int,
    val paymentSystem: String,
    val poster: String,
    val rateAvg: Int,
    val storeIdx: Int,
    val title: String,
    val url: String
)