package com.example.yogiyo_clone.src.order.menubottom.models


import com.google.gson.annotations.SerializedName

data class Top(
    val menuIdx: Int,
    val price: Int,
    val src: String,
    val title: String
)