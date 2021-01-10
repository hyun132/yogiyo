package com.example.yogiyo_clone.src.main.home.models


import com.google.gson.annotations.SerializedName

data class HomeResult(
    val address: String,
    val firstAd: List<FirstAd>,
    val middleAd: List<MiddleAd>,
    val themes: List<Theme>
)