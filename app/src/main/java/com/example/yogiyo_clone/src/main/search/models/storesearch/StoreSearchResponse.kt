package com.example.yogiyo_clone.src.main.search.models.storesearch


import com.google.gson.annotations.SerializedName

data class StoreSearchResponse(
    val code: Int,
    val message: String,
    val result: Result
)