package com.example.yogiyo_clone.src.order.menuheader

import com.example.yogiyo_clone.src.order.menuheader.model.MenuHeaderResponse
import retrofit2.Call
import retrofit2.http.*

interface MenuHeaderRetrofitInterface {

    @GET("/stores/{idx}")
    fun getMenuHeaderInfo(
        @Path("idx") idx: Int
        ) : Call<MenuHeaderResponse>

}
