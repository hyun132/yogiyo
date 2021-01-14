package com.example.yogiyo_clone.src.main.orderlist.models.history


import com.google.gson.annotations.SerializedName

data class Result(
    val orders: List<Order>?,
    val types: Types?
)