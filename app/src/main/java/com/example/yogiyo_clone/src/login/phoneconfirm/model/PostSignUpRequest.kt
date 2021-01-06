package com.example.yogiyo_clone.src.login.phoneconfirm.model

import com.google.gson.annotations.SerializedName

data class PostSignUpRequest(
        @SerializedName("email") var email: String,
        @SerializedName("pwd") val password: String,
        @SerializedName("nickname") val nickname: String,
        @SerializedName("push") val push: Int,
        @SerializedName("tel") var phoneNumber: String,
        @SerializedName("authNumber") var authNumber:Int
)