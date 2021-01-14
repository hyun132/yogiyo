package com.example.yogiyo_clone.src.order.menu.menudetail

import com.example.yogiyo_clone.src.order.menu.info.models.BottomInfoResponse
import com.example.yogiyo_clone.src.order.menu.menudetail.models.MenuResponse
import com.example.yogiyo_clone.src.order.menu.menuheader.model.MenuHeaderResponse
import retrofit2.Call
import retrofit2.http.*

interface MenuDetailRetrofitInterface {

    @GET("/menus/{idx}")
    fun getMenuDetail(
        @Path("idx") idx: Int
        ) : Call<MenuResponse>

}
