package com.example.yogiyo_clone.src.order.orderfood

import com.example.yogiyo_clone.src.order.orderfood.models.OrderRequest
import com.example.yogiyo_clone.src.order.orderfood.models.OrderResponse
import retrofit2.Call
import retrofit2.http.*

interface OrderRetrofitInterface {

    @POST("/orders")
    fun makeOrder(@Body params: OrderRequest): Call<OrderResponse>

}
