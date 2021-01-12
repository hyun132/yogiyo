package com.example.yogiyo_clone.src.order.menubottom.models


import com.google.gson.annotations.SerializedName

data class Menu(
    val description: String,
    val menuIdx: Int,
    val price: Int,
    val src: Any,
    val title: String
)