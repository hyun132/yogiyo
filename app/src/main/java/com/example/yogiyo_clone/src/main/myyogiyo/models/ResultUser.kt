package com.softsquared.template.kotlin.src.main.home.models

import com.google.gson.annotations.SerializedName

data class ResultUserInfo(
        //등급,닉네임,쿠폰,포인트,리뷰개수
        @SerializedName("userId") val userId: Int,
        @SerializedName("email") val email: String
)
