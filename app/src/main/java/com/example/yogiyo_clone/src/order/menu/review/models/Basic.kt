package com.example.yogiyo_clone.src.order.menu.review.models


import com.google.gson.annotations.SerializedName

data class Basic(
    val reviewCount: Int,
    val storeIdx: Int,
    val title: String
)