package com.example.yogiyo_clone.src.order.menubottom

import android.util.Log
import com.example.yogiyo_clone.config.ApplicationClass
import com.example.yogiyo_clone.src.login.login.model.LogInResponse
import com.example.yogiyo_clone.src.login.login.model.PostLogInRequest
import com.example.yogiyo_clone.src.order.menubottom.models.BottomMenuResponse
import com.example.yogiyo_clone.src.order.menuheader.model.MenuHeaderResponse
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
