package com.example.yogiyo_clone.src.main.orderlist.models.request


data class OrderRequestMenu(
    val menu: String,
    val menuPrice: Int,
    val orderRequestOptions: List<OrderRequestOption>,
    val quantity: Int,
    val totalPrice: Int
)