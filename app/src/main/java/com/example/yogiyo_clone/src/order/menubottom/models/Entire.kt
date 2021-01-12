package com.example.yogiyo_clone.src.order.menubottom.models


import com.google.gson.annotations.SerializedName

data class Entire(
    val groupName: String,
    val groupNum: Int,
    val menus: List<Menu>
)