package com.example.yogiyo_clone.src.main.home.models


import com.google.gson.annotations.SerializedName

data class Theme(
    val stores: List<Store>,
    val themeIdx: Int,
    val themeName: String
)