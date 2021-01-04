package com.example.yogiyo_clone.src.login.signupinfo.model

import com.google.gson.annotations.SerializedName


data class PostUserValicationResponse(
        @SerializedName("code")val code: Int?,
        @SerializedName("isSuccess")val isSuccess: Boolean?,
        @SerializedName("message")val message: String?,
)