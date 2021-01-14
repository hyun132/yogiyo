package com.example.yogiyo_clone.src.main.orderlist

import com.example.yogiyo_clone.config.ApplicationClass
import com.example.yogiyo_clone.src.main.orderlist.models.history.HistoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderListService(val view: OrderListFragment) { // 얘는 이전에 repository랑 비슷한 역할

    fun tryGetOrderList(type:Int){
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(OrderListRetrofitInterface::class.java)
        homeRetrofitInterface.getOrderList().enqueue(object : Callback<HistoryResponse>{
            override fun onResponse(
                call: Call<HistoryResponse>,
                response: Response<HistoryResponse>
            ) {
                response.body()?.let { view.onGetOrderListSuccess(it) }
            }

            override fun onFailure(call: Call<HistoryResponse>, t: Throwable) {
                t.message?.let { view.onGetOrderListFailure(it) }
            }

        })
    }


}
