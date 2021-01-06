package com.example.yogiyo_clone.src.main.home

import com.softsquared.template.kotlin.src.main.home.models.ElseResponse
import com.softsquared.template.kotlin.src.main.home.models.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface HomeRetrofitInterface {

    //광고, 카테고리, 테마들 받아옴.
    @GET("/users")
    fun getAds() : Call<UserResponse>

    @GET("/users")
    fun getCategorys() : Call<UserResponse>

    @GET("/users")
    fun getElses() : Call<ElseResponse>

}
