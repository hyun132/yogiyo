package com.example.yogiyo_clone.src.order.menubottom

import com.example.yogiyo_clone.src.order.menubottom.models.BottomMenuResponse
import com.example.yogiyo_clone.src.order.menuheader.model.MenuHeaderResponse
import retrofit2.Call
import retrofit2.http.*

interface BottomMenuRetrofitInterface {

    @GET("/stores/{idx}/menus")
    fun getBottomMenuInfo(
        @Path("idx") idx: Int
        ) : Call<BottomMenuResponse>

}
