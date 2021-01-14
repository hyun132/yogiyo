package com.example.yogiyo_clone.src.order.menu.info

import com.example.yogiyo_clone.config.ApplicationClass
import com.example.yogiyo_clone.src.order.menu.info.models.BottomInfoResponse
import com.example.yogiyo_clone.src.order.menu.menuheader.model.MenuHeaderResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantInfoService(val view: RestaurnatInfoFragment) { // 얘는 이전에 repository랑 비슷한 역할

    fun tryGetRestaurnatInfo(idx:Int){
        val restaurnatInfoRetrofitInterface = ApplicationClass.sRetrofit.create(RestaurnatInfoRetrofitInterface::class.java)
        restaurnatInfoRetrofitInterface.getRestaurantInfo(idx).enqueue(object : Callback<BottomInfoResponse>{
            override fun onResponse(
                call: Call<BottomInfoResponse>,
                response: Response<BottomInfoResponse>
            ) {
                response.body()?.let { view.onGetRestaurantInfoSuccess(it) }
            }

            override fun onFailure(call: Call<BottomInfoResponse>, t: Throwable) {
                t.message?.let { view.onGetRestaurantInfoFailure(it) }
            }


        })
    }

}
