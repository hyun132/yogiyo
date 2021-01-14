package com.example.yogiyo_clone.src.main.search.models.storesearch


import com.google.gson.annotations.SerializedName

data class Store(
    val cesco: String,
    val countOwnerComment: Int,
    val countReview: Int,
    val deliveryCharge: Int,
    val deliveryTime: String,
    val discountCharge: Int,
    val great: String,
    val icon: String,
    val isExpress: String,
    val limitCharge: Int,
    val menus: String,
    val storeIdx: Int,
    val title: String,
    val totalRate: Any
)