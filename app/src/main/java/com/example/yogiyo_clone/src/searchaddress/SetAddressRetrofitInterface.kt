package com.example.yogiyo_clone.src.searchaddress

import com.example.yogiyo_clone.src.searchaddress.mapSearch.model.PostSetMyAddressRequest
import com.example.yogiyo_clone.src.searchaddress.mapSearch.model.SetAddressResponse
import com.example.yogiyo_clone.src.searchaddress.textSearch.model.SearchAddressResponse
import retrofit2.Call
import retrofit2.http.*

interface SetAddressRetrofitInterface {

    @GET("/addresses")
    fun searchMyLocation(
        @Query("keyword",encoded = true) keyword: String
        ) : Call<SearchAddressResponse>

//    @POST("/addresses-join")
//    fun setMyLocation(@Body params: PostSetMyAddressRequest) : Call<SetAddressResponse>


    @POST("/addresses")
    fun setMyLocation(@Body params: PostSetMyAddressRequest) : Call<SetAddressResponse>

}
