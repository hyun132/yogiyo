package com.example.yogiyo_clone.src.order.menu.menudetail.models


import com.google.gson.annotations.SerializedName

data class EntireOption(
    val groupName: String,
    val groupNum: Int,
    val mandatory: String,
    val options: List<Option>
)