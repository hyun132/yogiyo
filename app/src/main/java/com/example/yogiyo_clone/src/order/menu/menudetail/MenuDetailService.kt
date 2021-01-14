package com.example.yogiyo_clone.src.order.menu.menudetail

import com.example.yogiyo_clone.config.ApplicationClass
import com.example.yogiyo_clone.src.order.menu.info.models.BottomInfoResponse
import com.example.yogiyo_clone.src.order.menu.menudetail.models.MenuResponse
import com.example.yogiyo_clone.src.order.menu.menuheader.model.MenuHeaderResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuDetailService(val view: MenuDetailFragment) { // 얘는 이전에 repository랑 비슷한 역할

    fun tryGetMenuDetail(idx:Int){
        val restaurnatInfoRetrofitInterface = ApplicationClass.sRetrofit.create(MenuDetailRetrofitInterface::class.java)
        restaurnatInfoRetrofitInterface.getMenuDetail(idx).enqueue(object : Callback<MenuResponse>{
            override fun onResponse(call: Call<MenuResponse>, response: Response<MenuResponse>) {
                response.body()?.let { view.onGetMenuDetailSuccess(it) }
            }

            override fun onFailure(call: Call<MenuResponse>, t: Throwable) {
                t.message?.let { view.onGetMenuDetailFailure(it) }
            }


        })
    }

}
