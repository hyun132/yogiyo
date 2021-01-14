package com.example.yogiyo_clone.src.order.orderfood

import com.example.yogiyo_clone.config.ApplicationClass
import com.example.yogiyo_clone.src.order.orderfood.models.OrderRequest
import com.example.yogiyo_clone.src.order.orderfood.models.OrderResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderService(val view: OrderFragment) { // 얘는 이전에 repository랑 비슷한 역할

    fun tryGetMenuDetail(orderRequest:OrderRequest){
        val restaurnatInfoRetrofitInterface = ApplicationClass.sRetrofit.create(OrderRetrofitInterface::class.java)
        restaurnatInfoRetrofitInterface.makeOrder(orderRequest).enqueue(object : Callback<OrderResponse>{
            override fun onResponse(call: Call<OrderResponse>, response: Response<OrderResponse>) {
                response.body()?.let { view.onPostOrderSuccess(it) }
            }

            override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                t.message?.let { view.onPostOrderFailure(it) }
            }


        })
    }

}
