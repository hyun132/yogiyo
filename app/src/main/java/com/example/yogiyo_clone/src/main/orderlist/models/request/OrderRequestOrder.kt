package com.example.yogiyo_clone.src.main.orderlist.models.request


import com.google.gson.annotations.SerializedName

data class OrderRequestOrder(
    val address: String,
    val date: String,
    val deliveryCharge: Int,
    val discountCharge: Int,
    val hasDisposable: String,
    val isAtDoor: String,
    val isSaFeTel: String,
    val nickname: String,
    val orderIdx: Int,
    val orderNumber: String,
    val originalCharge: Int,
    val paymentSystem: String,
    val saveUp: Int,
    val status: String,
    val storeIdx: Int,
    val storeTitle: String,
    val tel: String,
    val totalCharge: Int,
    val type: String,
    val userRequest: String
)