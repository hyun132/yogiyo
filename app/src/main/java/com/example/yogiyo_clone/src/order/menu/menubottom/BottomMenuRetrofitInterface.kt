package com.example.yogiyo_clone.src.order.menu.menubottom

import com.example.yogiyo_clone.src.order.menu.menubottom.models.BottomMenuResponse
import retrofit2.Call
import retrofit2.http.*

interface BottomMenuRetrofitInterface {

    @GET("/stores/{idx}/menus")
    fun getBottomMenuInfo(
        @Path("idx") idx: Int
        ) : Call<BottomMenuResponse>

}
