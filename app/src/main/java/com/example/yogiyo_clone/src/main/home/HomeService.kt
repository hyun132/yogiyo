package com.example.yogiyo_clone.src.main.home

import com.example.yogiyo_clone.config.ApplicationClass
import com.example.yogiyo_clone.src.main.home.models.HomeResponse
import com.example.yogiyo_clone.src.main.home.models.bottom.BottomRecommandResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeService(val view: HomeFragmentView) { // 얘는 이전에 repository랑 비슷한 역할

    fun tryGetHomeInfo() {
        val homeRetrofitInterface =
            ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
        homeRetrofitInterface.getHomeInfo().enqueue(object : Callback<HomeResponse> {
            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {

                response.body()?.let {
                        view.onGetHomeInfoSuccess(it)}

            }

            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                t.message?.let { view.onGetHomeInfoFailure(it) }
            }
        })
    }

    fun tryGetHomeBottomRecommand() {
        val homeRetrofitInterface =
            ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
        homeRetrofitInterface.getHomeBottomRecommend().enqueue(object : Callback<BottomRecommandResponse> {
            override fun onResponse(
                call: Call<BottomRecommandResponse>,
                response: Response<BottomRecommandResponse>
            ) {
                response.body()?.let { view.onGetBottomRecommadSuccess(it) }
            }

            override fun onFailure(call: Call<BottomRecommandResponse>, t: Throwable) {
                t.message?.let { view.onGetBottomRecommadFailure(it) }
            }

        })
    }

}
