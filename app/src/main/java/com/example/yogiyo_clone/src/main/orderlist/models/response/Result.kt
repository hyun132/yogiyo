package com.example.yogiyo_clone.src.main.orderlist.models.response


import com.google.gson.annotations.SerializedName

data class Result(
    val address: String,
    val stores: List<Store>
)