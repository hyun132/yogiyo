package com.example.yogiyo_clone.src.searchaddress.mapSearch

import com.example.yogiyo_clone.src.searchaddress.mapSearch.model.PostSetMyAddressRequest
import com.example.yogiyo_clone.src.searchaddress.mapSearch.model.SetAddressResponse
import retrofit2.Call
import retrofit2.http.*

interface SetMapAddressRetrofitInterface {

    @POST("/addresses")
    fun setMyLocation(@Body params: PostSetMyAddressRequest) : Call<SetAddressResponse>

}
