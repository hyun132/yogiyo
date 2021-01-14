package com.example.yogiyo_clone.src.main.home.models.bottom


data class RecommandResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val recommandResult: List<RecommandResult>
)