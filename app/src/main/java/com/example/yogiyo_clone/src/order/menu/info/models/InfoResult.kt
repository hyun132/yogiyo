package com.example.yogiyo_clone.src.order.menu.info.models


import com.google.gson.annotations.SerializedName

data class InfoResult(
    val address: String?,
    val businessHour: String?,
    val businessName: String?,
    val businessNumber: String?,
    val cesco: String?,
    val countryOfOrigin: String?,
    val deliveryCharge: String?,
    val description: String?,
    val great: String?,
    val limitCharge: String?,
    val paymentSystem: String?,
    val storeIdx: String?,
    val tel: String?
)