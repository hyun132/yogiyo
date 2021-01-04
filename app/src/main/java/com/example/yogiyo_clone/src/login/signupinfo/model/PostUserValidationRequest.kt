package com.example.yogiyo_clone.src.login.signupinfo.model

import com.google.gson.annotations.SerializedName

data class PostUserValidationRequest(
        @SerializedName("email") var email: String,
        @SerializedName("pwd") val password: String,
        @SerializedName("pwdCheck") val checkPassword: String,
)