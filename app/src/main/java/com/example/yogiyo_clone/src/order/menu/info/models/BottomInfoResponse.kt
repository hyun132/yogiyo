package com.example.yogiyo_clone.src.order.menu.info.models


data class BottomInfoResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: InfoResult
)