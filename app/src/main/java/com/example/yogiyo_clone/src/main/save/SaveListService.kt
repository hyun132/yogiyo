package com.example.yogiyo_clone.src.main.save

import com.example.yogiyo_clone.config.ApplicationClass
import com.example.yogiyo_clone.src.main.save.models.SaveListResponse
import kotlinx.coroutines.flow.combine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SaveListService(val view: SaveListFragment) { // 얘는 이전에 repository랑 비슷한 역할

    fun tryGetSaveList() {
        val homeRetrofitInterface =
            ApplicationClass.sRetrofit.create(SaveListRetrofitInterface::class.java)
        homeRetrofitInterface.getSaveList().enqueue(object : Callback<SaveListResponse> {
            override fun onResponse(
                call: Call<SaveListResponse>,
                response: Response<SaveListResponse>
            ) {
                response.body()?.let { view.onGetSaveListSuccess(it) }
            }

            override fun onFailure(call: Call<SaveListResponse>, t: Throwable) {
                t.message?.let { view.onGetSaveListFailure(it) }
            }

        })
    }
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


