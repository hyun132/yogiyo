package com.example.yogiyo_clone.src.main.save

import com.example.yogiyo_clone.src.main.save.models.SaveListResponse
import retrofit2.Call
import retrofit2.http.*

interface SaveListRetrofitInterface {

    //광고, 카테고리, 테마들 받아옴.
    @GET("/liked-stores")
    fun getSaveList() : Call<SaveListResponse>


}
