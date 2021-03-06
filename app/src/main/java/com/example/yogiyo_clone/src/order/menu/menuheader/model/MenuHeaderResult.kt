package com.example.yogiyo_clone.src.order.menu.menuheader.model


data class MenuHeaderResult(
    val countLike: Int?,
    val deliveryCharge: Int,
    val deliveryTime: String?,
    val description: String?,
    val discountCharge: Int,
    val isExpress: String?,
    val isLike: String?,
    val limitCharge: Int,
    val paymentSystem: String?,
    val poster: String?,
    val rateAvg: Double?,
    val storeIdx: Int,
    val title: String?,
    val url: String?
)