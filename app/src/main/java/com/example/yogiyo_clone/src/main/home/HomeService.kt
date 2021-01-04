package com.example.yogiyo_clone.src.main.home

import android.location.Location
import com.example.yogiyo_clone.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.home.models.ElseResponse
import com.softsquared.template.kotlin.src.main.home.models.PostSignUpRequest
import com.softsquared.template.kotlin.src.main.home.models.SignUpResponse
import com.softsquared.template.kotlin.src.main.home.models.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeService(val view: HomeFragmentView) { // 얘는 이전에 repository랑 비슷한 역할

    fun tryGetAds(){
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
        homeRetrofitInterface.getAds().enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                view.onGetUserSuccess(response.body() as UserResponse)
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                view.onGetUserFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryGetCategories(loc:Location){
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
        homeRetrofitInterface.getElses().enqueue(object : Callback<ElseResponse>{
            override fun onResponse(call: Call<ElseResponse>, response: Response<ElseResponse>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<ElseResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun tryGetElse(loc:Location){
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
        homeRetrofitInterface.getElses().enqueue(object : Callback<ElseResponse>{
            override fun onResponse(call: Call<ElseResponse>, response: Response<ElseResponse>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<ElseResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

}
