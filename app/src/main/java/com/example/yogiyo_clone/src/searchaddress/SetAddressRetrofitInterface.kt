package com.example.yogiyo_clone.src.searchaddress

import com.example.yogiyo_clone.src.searchaddress.mapSearch.model.PostSetMyAddressRequest
import com.example.yogiyo_clone.src.searchaddress.textSearch.model.SearchAddressResponse
import com.example.yogiyo_clone.src.searchaddress.model.SetAddressResponse
import retrofit2.Call
import retrofit2.http.*

interface SetAddressRetrofitInterface {

    @POST("/addresses")
    fun searchMyLocation(
        @Query("keyword") keyword: String
        ) : Call<SearchAddressResponse>

    @POST("/addresses-join")
    fun setMyLocation(@Body params: PostSetMyAddressRequest) : Call<SetAddressResponse>


}
