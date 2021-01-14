package com.example.yogiyo_clone.src.main.save.models


import com.google.gson.annotations.SerializedName

data class SaveListResponse(
    val code: Int,
    val message: String,
    val result: Result
)