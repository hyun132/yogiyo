package com.example.yogiyo_clone.src.main.orderlist.models.history


import com.google.gson.annotations.SerializedName

data class HistoryResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result?
)