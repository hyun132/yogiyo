package com.example.yogiyo_clone.src.main.search.models.menusearch


import com.google.gson.annotations.SerializedName

data class Menu(
    val countMenuReview: Int,
    val countStoreReview: Int,
    val deliveryTime: String,
    val limitCharge: Int,
    val menuIdx: Int,
    val menuTitle: String,
    val price: Int,
    val review: Any,
    val src: String,
    val storeIdx: Int,
    val storeTitle: String,
    val tasteRate: Int
)