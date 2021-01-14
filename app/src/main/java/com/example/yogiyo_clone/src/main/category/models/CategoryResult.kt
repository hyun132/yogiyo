package com.example.yogiyo_clone.src.main.category.models


import com.google.gson.annotations.SerializedName

data class CategoryResult(
    val address: String?,
    val stores: List<Store>?
)