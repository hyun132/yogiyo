package com.example.yogiyo_clone.src.order.menu.menudetail.models


import com.google.gson.annotations.SerializedName

data class MenuResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)