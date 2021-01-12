package com.example.yogiyo_clone.src.order.menu.review.models


import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)