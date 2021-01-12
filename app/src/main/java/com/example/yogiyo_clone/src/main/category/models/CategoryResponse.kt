package com.example.yogiyo_clone.src.main.category.models


data class CategoryResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: CategoryResult
)