package com.example.yogiyo_clone.src.searchaddress.mapSearch

import com.example.yogiyo_clone.config.ApplicationClass
import com.example.yogiyo_clone.src.searchaddress.SetAddressRetrofitInterface
import com.example.yogiyo_clone.src.searchaddress.mapSearch.model.PostSetMyAddressRequest
import com.example.yogiyo_clone.src.searchaddress.mapSearch.model.SetAddressResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SetAddressService(val view: SearchMapFragment) { // 얘는 이전에 repository랑 비슷한 역할

    fun setAddress(postSetMyAddressRequest: PostSetMyAddressRequest){
        val setMyAddressResrofitInterface = ApplicationClass.sRetrofit.create(
            SetAddressRetrofitInterface::class.java
        )
        setMyAddressResrofitInterface.setMyLocation(postSetMyAddressRequest).enqueue(object :Callback<SetAddressResponse>{
            override fun onResponse(
                call: Call<SetAddressResponse>,
                response: Response<SetAddressResponse>
            ) {
                response.body()?.let { view.onPostSetMapAddressSuccess(it) }
            }

            override fun onFailure(call: Call<SetAddressResponse>, t: Throwable) {
                t.message?.let { it1 -> view.onPostSetMapAddressFailure(it1) }
            }


        })


    }

}
