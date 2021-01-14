package com.example.yogiyo_clone.src.main.save.models


import com.google.gson.annotations.SerializedName

data class Result(
    val countStroe: Int,
    val stores: List<Store>
)