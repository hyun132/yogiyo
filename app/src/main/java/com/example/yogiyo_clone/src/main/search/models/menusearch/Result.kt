package com.example.yogiyo_clone.src.main.search.models.menusearch


import com.google.gson.annotations.SerializedName

data class Result(
    val countMenu: Int,
    val menus: List<Menu>
)