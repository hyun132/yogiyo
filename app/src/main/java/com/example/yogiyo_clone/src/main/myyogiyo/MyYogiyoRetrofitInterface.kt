package com.example.yogiyo_clone.src.main.myyogiyo

import com.example.yogiyo_clone.src.main.myyogiyo.models.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface MyYogiyoRetrofitInterface {
    @GET("/users")
    fun getUsers() : Call<UserResponse>
}
