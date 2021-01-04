package com.example.yogiyo_clone.src.main.myyogiyo

import com.example.yogiyo_clone.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.home.models.PostSignUpRequest
import com.softsquared.template.kotlin.src.main.home.models.SignUpResponse
import com.softsquared.template.kotlin.src.main.home.models.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyYogiyoService(val view: MyYogiyoFragment) { // 얘는 이전에 repository랑 비슷한 역할

    fun tryGetUserInfo(){
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(MyYogiyoRetrofitInterface::class.java)
        homeRetrofitInterface.getUsers().enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                view.onGetUserInfoSuccess(response.body() as UserResponse)
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                view.onGetUserInfoFailure(t.message ?: "통신 오류")
            }
        })
    }


}
