package com.example.yogiyo_clone.src.order.orderfood.models


data class Order(
    val count: Int,
    val menuIdx: Int,
    val options: String
)