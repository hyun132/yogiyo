package com.example.yogiyo_clone.src.main.home

import com.example.yogiyo_clone.src.main.home.models.HomeResponse
import com.example.yogiyo_clone.src.main.home.models.bottom.BottomRecommandResponse
import retrofit2.Call
import retrofit2.http.*

interface HomeRetrofitInterface {

    //광고, 카테고리, 테마들 받아옴.
    @GET("/main")
    fun getHomeInfo() : Call<HomeResponse>

    @GET("/main/today-recommendation")
    fun getHomeBottomRecommend() : Call<BottomRecommandResponse>

//    @GET("/users")
//    fun getCategorys() : Call<UserResponse>
//
//    @GET("/users")
//    fun getElses() : Call<ElseResponse>

}
