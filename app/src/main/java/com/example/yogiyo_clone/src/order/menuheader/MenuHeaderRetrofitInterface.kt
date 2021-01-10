package com.example.yogiyo_clone.src.order.menuheader

import com.example.yogiyo_clone.src.order.menuheader.model.MenuHeaderResponse
import com.example.yogiyo_clone.src.searchaddress.mapSearch.model.PostSetMyAddressRequest
import com.example.yogiyo_clone.src.searchaddress.textSearch.model.SearchAddressResponse
import com.example.yogiyo_clone.src.searchaddress.model.SetAddressResponse
import retrofit2.Call
import retrofit2.http.*

interface MenuHeaderRetrofitInterface {

    @GET("/stores/{idx}")
    fun getMenuHeaderInfo(
        @Path("idx") idx: Int
        ) : Call<MenuHeaderResponse>


}
