package com.example.yogiyo_clone.src.main.orderlist.models.history


data class OrderHistoryResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val historyResult: HistoryResult?
)