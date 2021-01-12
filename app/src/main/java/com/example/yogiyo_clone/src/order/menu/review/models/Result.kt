package com.example.yogiyo_clone.src.order.menu.review.models


import com.google.gson.annotations.SerializedName

data class Result(
    val basic: Basic,
    val entire: List<Entire>,
    val photos: List<Photo>,
    val rates: Rates
)