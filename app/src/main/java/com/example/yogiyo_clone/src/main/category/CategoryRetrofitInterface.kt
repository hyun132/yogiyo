package com.example.yogiyo_clone.src.main.category

import com.example.yogiyo_clone.src.login.login.model.LogInResponse
import com.example.yogiyo_clone.src.login.login.model.PostLogInRequest
import com.example.yogiyo_clone.src.main.category.models.CategoryRequest
import com.example.yogiyo_clone.src.main.category.models.CategoryResponse
import com.example.yogiyo_clone.src.searchaddress.textSearch.model.SearchAddressResponse
import retrofit2.Call
import retrofit2.http.*

interface CategoryRetrofitInterface {

    //
    @GET("/stores")
    fun getCategoryInfo(
        @Query("categoryId",encoded = true) categoryId: Int,
        @Query("express",encoded = true) isExpress: Boolean,
        @Query("sortId",encoded = true) sortId: Int,
        @Query("paymentId",encoded = true) pay_method: Int,
    ) : Call<CategoryResponse>


}
