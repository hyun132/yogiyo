package com.example.yogiyo_clone.src.login.phoneconfirm.model

import com.google.gson.annotations.SerializedName


data class SignUpResponse(
        @SerializedName("code")val code: Int?,
        @SerializedName("isSuccess")val isSuccess: Boolean?,
        @SerializedName("message")val message: String?,
)