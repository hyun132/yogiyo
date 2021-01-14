package com.example.yogiyo_clone.src.main.search.models.storesearch


import com.google.gson.annotations.SerializedName

data class Result(
    val countStore: Int,
    val stores: List<Store>
)