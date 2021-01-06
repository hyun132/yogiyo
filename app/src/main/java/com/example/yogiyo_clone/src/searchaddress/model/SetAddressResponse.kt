package com.example.yogiyo_clone.src.searchaddress.model

import com.google.gson.annotations.SerializedName


data class SetAddressResponse(
        @SerializedName("code")val code: Int?,
        @SerializedName("isSuccess")val isSuccess: Boolean?,
        @SerializedName("message")val message: String?,
)