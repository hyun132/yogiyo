package com.example.yogiyo_clone.src.order.orderfood.models


data class OrderRequest(
    val address: String,
    val deliveryCharge: Int,
    val discountCharge: Int,
    val hasDisposable: Int,
    val isAtDoor: Int,
    val isSaveTel: Int,
    val orderList: List<Order>,
    val originalCharge: Int,
    val paymentSystemIdx: Int,
    val receipt: String,
    val storeIdx: Int,
    val tel: String,
    val totalCharge: Int,
    val type: Int,
    val userRequest: String
)