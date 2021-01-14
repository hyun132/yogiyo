package com.example.yogiyo_clone.src.order.menu.menudetail.models


import com.google.gson.annotations.SerializedName

data class Menu(
    val description: String,
    val menuIdx: Int,
    val price: Int,
    val reviewCount: Int,
    val src: String,
    val title: String
)