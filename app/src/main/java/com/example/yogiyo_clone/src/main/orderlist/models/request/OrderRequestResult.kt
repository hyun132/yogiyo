package com.example.yogiyo_clone.src.main.orderlist.models.request


data class OrderRequestResult(
    val orderRequestMenus: List<OrderRequestMenu>,
    val orderRequestOrder: OrderRequestOrder
)