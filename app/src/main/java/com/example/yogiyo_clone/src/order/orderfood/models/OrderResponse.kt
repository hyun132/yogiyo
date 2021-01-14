package com.example.yogiyo_clone.src.order.orderfood.models


data class OrderResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Int
)