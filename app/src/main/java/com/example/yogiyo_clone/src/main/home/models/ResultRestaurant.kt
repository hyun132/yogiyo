package com.softsquared.template.kotlin.src.main.home.models

import com.google.gson.annotations.SerializedName

data class ResultRestaurant(
        @SerializedName("restaurant_name") val restaurant_name: Int,
        @SerializedName("email") val email: String
)
