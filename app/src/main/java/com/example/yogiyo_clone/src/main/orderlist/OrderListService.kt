package com.example.yogiyo_clone.src.main.orderlist

import com.example.yogiyo_clone.config.ApplicationClass
import com.example.yogiyo_clone.src.main.orderlist.models.history.OrderHistoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderListService(val view: OrderListFragment) { // 얘는 이전에 repository랑 비슷한 역할

    fun tryGetOrderList(type:Int){
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(OrderListRetrofitInterface::class.java)
        homeRetrofitInterface.getOrderList().enqueue(object : Callback<OrderHistoryResponse>{
            override fun onResponse(
                call: Call<OrderHistoryResponse>,
                response: Response<OrderHistoryResponse>
            ) {
                response.body()?.historyResult?.let { view.onGetOrderListSuccess(it) }
            }

            override fun onFailure(call: Call<OrderHistoryResponse>, t: Throwable) {
                t.message?.let { view.onGetOrderListFailure(it) }
            }

        })
    }


}
