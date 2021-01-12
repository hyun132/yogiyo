package com.example.yogiyo_clone.src.order.menu.review.models


import com.google.gson.annotations.SerializedName

data class Rates(
    val amountRate: Double,
    val deliveryRate: Double,
    val tasteRate: Double,
    val totalRate: Double
)