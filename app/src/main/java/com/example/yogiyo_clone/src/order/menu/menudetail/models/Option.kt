package com.example.yogiyo_clone.src.order.menu.menudetail.models


import com.google.gson.annotations.SerializedName

data class Option(
    val optionIdx: Int,
    val price: Int,
    val title: String
)