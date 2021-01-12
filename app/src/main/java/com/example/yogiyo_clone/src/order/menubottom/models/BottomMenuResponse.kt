package com.example.yogiyo_clone.src.order.menubottom.models


import com.google.gson.annotations.SerializedName

data class BottomMenuResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)