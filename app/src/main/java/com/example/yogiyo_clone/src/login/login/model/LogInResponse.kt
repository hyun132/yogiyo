package com.example.yogiyo_clone.src.login.login.model

import com.google.gson.annotations.SerializedName


data class LogInResponse(
        @SerializedName("code")val code: Int?,
        @SerializedName("isSuccess")val isSuccess: Boolean?,
        @SerializedName("message")val message: String?,
        @SerializedName("result")val result: LogInResult?
)