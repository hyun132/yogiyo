package com.example.yogiyo_clone.src.main.home

import android.location.Location
import com.example.yogiyo_clone.config.ApplicationClass
import com.example.yogiyo_clone.src.main.home.models.HomeResponse
import kotlinx.coroutines.flow.combine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeService(val view: HomeFragmentView) { // 얘는 이전에 repository랑 비슷한 역할

    fun tryGetHomeInfo(){
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
        homeRetrofitInterface.getHomeInfo().enqueue(object : Callback<HomeResponse>{
            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
                response.body()?.let { view.onGetHomeInfoSuccess(it) }
            }

            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                t.message?.let { view.onGetHomeInfoFailure(it) }
            }
        })
    }

//    fun tryGetCategories(loc:Location){
//        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
//        homeRetrofitInterface.getElses().enqueue(object : Callback<ElseResponse>{
//            override fun onResponse(call: Call<ElseResponse>, response: Response<ElseResponse>) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onFailure(call: Call<ElseResponse>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//        })
//    }
//
//    fun tryGetElse(loc:Location){
//        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
//        homeRetrofitInterface.getElses().enqueue(object : Callback<ElseResponse>{
//            override fun onResponse(call: Call<ElseResponse>, response: Response<ElseResponse>) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onFailure(call: Call<ElseResponse>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//        })
//    }

}
