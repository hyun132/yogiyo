package com.example.yogiyo_clone.src.order.menubottom.models


import com.google.gson.annotations.SerializedName

data class Basic(
    val menuCount: Int,
    val storeIdx: Int,
    val title: String
)