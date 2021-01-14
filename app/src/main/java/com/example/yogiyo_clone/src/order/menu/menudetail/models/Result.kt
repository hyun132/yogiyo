package com.example.yogiyo_clone.src.order.menu.menudetail.models


import com.google.gson.annotations.SerializedName

data class Result(
    val entireOption: List<EntireOption>,
    val menu: Menu
)