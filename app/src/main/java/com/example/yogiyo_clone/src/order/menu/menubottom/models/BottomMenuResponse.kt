package com.example.yogiyo_clone.src.order.menu.menubottom.models


data class BottomMenuResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)