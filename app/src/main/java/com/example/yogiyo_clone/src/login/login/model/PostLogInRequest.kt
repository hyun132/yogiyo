package com.example.yogiyo_clone.src.login.login.model

import com.google.gson.annotations.SerializedName

data class PostLogInRequest(
        @SerializedName("email") var email: String,
        @SerializedName("pwd") val password: String,
)