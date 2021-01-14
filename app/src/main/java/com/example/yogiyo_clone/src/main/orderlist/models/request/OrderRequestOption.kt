package com.example.yogiyo_clone.src.main.orderlist.models.request


import com.google.gson.annotations.SerializedName

data class OrderRequestOption(
    val option: String,
    val optionPrice: Int
)