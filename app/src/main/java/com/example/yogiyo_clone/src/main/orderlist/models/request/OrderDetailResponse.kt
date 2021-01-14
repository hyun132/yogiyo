package com.example.yogiyo_clone.src.main.orderlist.models.request


data class OrderDetailResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val orderResult: OrderRequestResult
)