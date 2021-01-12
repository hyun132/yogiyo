package com.example.yogiyo_clone.src.searchaddress.mapSearch.model

import com.google.gson.annotations.SerializedName

data class PostSetMyAddressRequest(
        @SerializedName("lat") var lat: Float,
        @SerializedName("lng") var lng: Float,
        @SerializedName("jibunAddr") var jibunaddr: String,
        @SerializedName("roadAddr") var roadAddr: String,
        @SerializedName("detailAddr") var deatilAddr: String,
)