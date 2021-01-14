package com.example.yogiyo_clone.src.main.orderlist

import com.example.yogiyo_clone.src.main.orderlist.models.history.OrderHistoryResponse
import retrofit2.Call
import retrofit2.http.*

interface OrderListRetrofitInterface {
    @GET("/orders")
    fun getOrderList() : Call<OrderHistoryResponse>
}
