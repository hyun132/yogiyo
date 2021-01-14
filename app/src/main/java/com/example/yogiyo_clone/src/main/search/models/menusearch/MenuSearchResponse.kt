package com.example.yogiyo_clone.src.main.search.models.menusearch


import com.google.gson.annotations.SerializedName

data class MenuSearchResponse(
    val code: Int,
    val message: String,
    val result: Result
)