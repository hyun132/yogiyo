package com.example.yogiyo_clone.src.main.search

import com.example.yogiyo_clone.src.main.orderlist.models.history.HistoryResponse
import com.example.yogiyo_clone.src.main.search.models.menusearch.MenuSearchResponse
import com.example.yogiyo_clone.src.main.search.models.storesearch.StoreSearchResponse
import retrofit2.Call
import retrofit2.http.*

interface MainSearchRetrofitInterface {
    @GET("/search/menus")
    fun getMenuSearchList(
        @Query("keyword")keyword:String,
        @Query("sortId")sortId:Int?=0,
        @Query("limit")limit:Int?=0,

    ) : Call<MenuSearchResponse>

    @GET("/search/stores")
    fun getStoreSearchList(
        @Query("keyword")keyword:String,
        @Query("sortId")sortId:Int?=0,
        @Query("limit")limit:Int?=0,
        @Query("takeout")takeout:Int?=0,
        @Query("express")express:Int?=0,
    ) : Call<StoreSearchResponse>
}
