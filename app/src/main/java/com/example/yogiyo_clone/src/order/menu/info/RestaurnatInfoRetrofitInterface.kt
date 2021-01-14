package com.example.yogiyo_clone.src.order.menu.info

import com.example.yogiyo_clone.src.order.menu.info.models.BottomInfoResponse
import com.example.yogiyo_clone.src.order.menu.menuheader.model.MenuHeaderResponse
import retrofit2.Call
import retrofit2.http.*

interface RestaurnatInfoRetrofitInterface {

    @GET("/stores/{idx}/infos")
    fun getRestaurantInfo(
        @Path("idx") idx: Int
        ) : Call<BottomInfoResponse>

}
