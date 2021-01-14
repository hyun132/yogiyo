package com.example.yogiyo_clone.src.main.home.models.bottom


import com.google.gson.annotations.SerializedName

data class BottomRecommandResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>?
)