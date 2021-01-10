package com.example.yogiyo_clone.src.order.menuheader.model


data class MenuHeaderResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: MenuHeaderResult
)