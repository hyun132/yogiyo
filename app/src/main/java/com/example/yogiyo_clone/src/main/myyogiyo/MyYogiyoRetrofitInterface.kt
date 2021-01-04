package com.example.yogiyo_clone.src.main.myyogiyo

import com.softsquared.template.kotlin.src.main.home.models.PostSignUpRequest
import com.softsquared.template.kotlin.src.main.home.models.SignUpResponse
import com.softsquared.template.kotlin.src.main.home.models.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface MyYogiyoRetrofitInterface {
    @GET("/users")
    fun getUsers() : Call<UserResponse>
}
