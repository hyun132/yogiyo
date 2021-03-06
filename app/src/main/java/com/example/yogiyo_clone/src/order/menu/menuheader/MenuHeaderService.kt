package com.example.yogiyo_clone.src.order.menu.menuheader

import com.example.yogiyo_clone.config.ApplicationClass
import com.example.yogiyo_clone.src.order.menu.menuheader.model.JjimRequest
import com.example.yogiyo_clone.src.order.menu.menuheader.model.JjimResponse
import com.example.yogiyo_clone.src.order.menu.menuheader.model.MenuHeaderResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuHeaderService(val view: MenuHeaderFragment) { // 얘는 이전에 repository랑 비슷한 역할

    fun tryLogIn(idx:Int){
        val menuHeaderRetrofitInterface = ApplicationClass.sRetrofit.create(MenuHeaderRetrofitInterface::class.java)
        menuHeaderRetrofitInterface.getMenuHeaderInfo(idx).enqueue(object : Callback<MenuHeaderResponse>{
            override fun onResponse(
                call: Call<MenuHeaderResponse>,
                response: Response<MenuHeaderResponse>
            ) {
                response.body()?.let { view.onGetMenuHeaderSuccess(it) }
            }
            override fun onFailure(call: Call<MenuHeaderResponse>, t: Throwable) {
                t.message?.let { view.onGetMenuHeaderFailure(it) }
            }

        })
    }

    fun tryJjim(idx:Int){
        val jjimRetrofitInterface = ApplicationClass.sRetrofit.create(MenuHeaderRetrofitInterface::class.java)
        jjimRetrofitInterface.postJjim(JjimRequest(idx)).enqueue(object : Callback<JjimResponse>{
            override fun onResponse(call: Call<JjimResponse>, response: Response<JjimResponse>) {
                response.body()?.let { view.onPostJjimSuccess(it) }
            }

            override fun onFailure(call: Call<JjimResponse>, t: Throwable) {
                t.message?.let { view.onPostJjimFailure(it) }
            }


        })
    }

}
