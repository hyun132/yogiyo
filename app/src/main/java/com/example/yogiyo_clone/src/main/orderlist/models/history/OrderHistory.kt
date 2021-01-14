package com.example.yogiyo_clone.src.main.orderlist.models.history


import com.google.gson.annotations.SerializedName

data class OrderHistory(
    val date: String,
    val menu: String,
    val orderIdx: Int?,
    val status: String?,
    val storeIcon: String?,
    val storeIdx: Int?,
    val storeTitle: String?,
    val type: String?
)