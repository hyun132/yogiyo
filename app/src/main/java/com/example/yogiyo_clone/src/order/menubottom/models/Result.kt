package com.example.yogiyo_clone.src.order.menubottom.models


import com.google.gson.annotations.SerializedName

data class Result(
    val basic: Basic,
    val entire: List<Entire>,
    val tops: List<Top>
)