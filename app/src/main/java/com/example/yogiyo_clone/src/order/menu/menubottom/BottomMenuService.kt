package com.example.yogiyo_clone.src.order.menu.menubottom

import com.example.yogiyo_clone.config.ApplicationClass
import com.example.yogiyo_clone.src.order.menu.menubottom.models.BottomMenuResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BottomMenuService(val view: BottomMenuFragment) { // 얘는 이전에 repository랑 비슷한 역할

    fun tryGetBottomMenu(idx:Int){
        val bottomMenuRetrofitInterface = ApplicationClass.sRetrofit.create(BottomMenuRetrofitInterface::class.java)
        bottomMenuRetrofitInterface.getBottomMenuInfo(idx).enqueue(object : Callback<BottomMenuResponse>{
            override fun onResponse(
                call: Call<BottomMenuResponse>,
                response: Response<BottomMenuResponse>
            ) {
                response.body()?.let { view.onGetBottomMenuSuccess(it) }
            }

            override fun onFailure(call: Call<BottomMenuResponse>, t: Throwable) {
                t.message?.let { view.onGetBottomMenuFailure(it) }
            }


        })
    }

}
