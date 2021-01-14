package com.example.yogiyo_clone.src.main.orderlist.models.response


import com.google.gson.annotations.SerializedName

data class OrderHistoryResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)