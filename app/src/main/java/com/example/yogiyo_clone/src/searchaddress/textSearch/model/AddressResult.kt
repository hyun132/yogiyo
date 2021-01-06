package com.example.yogiyo_clone.src.searchaddress.textSearch.model


import com.google.gson.annotations.SerializedName

data class AddressResult(
    val jibunAddr: String,
    val lat: Double,
    val lng: Double,
    val roadAddr: String
)