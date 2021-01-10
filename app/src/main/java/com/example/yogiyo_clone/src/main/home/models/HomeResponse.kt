package com.example.yogiyo_clone.src.main.home.models


data class HomeResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: HomeResult
)