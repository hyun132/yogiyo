package com.example.yogiyo_clone.src.main.search

import com.example.yogiyo_clone.config.ApplicationClass
import com.example.yogiyo_clone.src.main.save.models.SaveListResponse
import com.example.yogiyo_clone.src.main.search.models.menusearch.MenuSearchResponse
import com.example.yogiyo_clone.src.main.search.models.storesearch.StoreSearchResponse
import kotlinx.coroutines.flow.combine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainSearchService(val view: SearchFragment) { // 얘는 이전에 repository랑 비슷한 역할

    fun tryGetSearchMenuList(keyword:String,searchId:Int?,limit:Int?) {
        val homeRetrofitInterface =
            ApplicationClass.sRetrofit.create(MainSearchRetrofitInterface::class.java)
        homeRetrofitInterface.getMenuSearchList(keyword,searchId,limit).enqueue(object : Callback<MenuSearchResponse> {
            override fun onResponse(
                call: Call<MenuSearchResponse>,
                response: Response<MenuSearchResponse>
            ) {
                response.body()?.let { view.onGetMenuSearchSuccess(it) }
            }

            override fun onFailure(call: Call<MenuSearchResponse>, t: Throwable) {
                t.message?.let { view.onGetMenuSearchFailure(it) }
            }

        })
    }

    fun tryGetSearchStoreList(keyword:String,sortId:Int?,limit:Int?,express:Int?,takeout:Int?) {
        val homeRetrofitInterface =
            ApplicationClass.sRetrofit.create(MainSearchRetrofitInterface::class.java)
        homeRetrofitInterface.getStoreSearchList(keyword,sortId,limit,express,takeout).enqueue(object : Callback<StoreSearchResponse> {
            override fun onResponse(
                call: Call<StoreSearchResponse>,
                response: Response<StoreSearchResponse>
            ) {
                response.body()?.let { view.onGetStoreSearchSuccess(it) }
            }

            override fun onFailure(call: Call<StoreSearchResponse>, t: Throwable) {
                t.message?.let { view.onGetStoreSearchFailure(it) }
            }

        })
    }
}


