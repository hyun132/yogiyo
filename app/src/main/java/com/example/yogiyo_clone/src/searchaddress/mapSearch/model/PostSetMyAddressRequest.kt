package com.example.yogiyo_clone.src.searchaddress.mapSearch.model

import com.google.gson.annotations.SerializedName

data class PostSetMyAddressRequest(
        @SerializedName("lat") var lat: Float,
        @SerializedName("lng") var lng: Float,
        @SerializedName("addr") var addr: String,
        @SerializedName("addr2") var deatilAddr: String,
)