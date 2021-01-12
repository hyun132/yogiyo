package com.example.yogiyo_clone.src.main.category.models

import com.google.gson.annotations.SerializedName
import retrofit2.http.Query

data class CategoryRequest(
        @SerializedName("categoryId") val categoryId: Int,
        @SerializedName("express") val isExpress: Boolean,
        @SerializedName("sortId") val sortId: Int,
        @SerializedName("paymentId") val pay_method: Int,
)