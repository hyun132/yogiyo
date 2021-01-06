package com.example.yogiyo_clone.src.login.phoneconfirm.model

import com.google.gson.annotations.SerializedName

data class PostphoneAuthRequest(
        @SerializedName("tel") var phoneNumber: String,
)