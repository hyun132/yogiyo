package com.example.yogiyo_clone.src.searchaddress

import com.example.yogiyo_clone.config.ApplicationClass
import com.example.yogiyo_clone.src.searchaddress.mapSearch.model.PostSetMyAddressRequest
import com.example.yogiyo_clone.src.searchaddress.textSearch.model.SearchAddressResponse
import com.example.yogiyo_clone.src.searchaddress.model.SetAddressResponse
import com.example.yogiyo_clone.src.searchaddress.textSearch.SearchTextAddressFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchAddressService(val view: SearchTextAddressFragment) { // 얘는 이전에 repository랑 비슷한 역할

    fun trySearchAddressByText(keyword:String){
        val setAddressRetrofitInterface = ApplicationClass.sRetrofit.create(
            SetAddressRetrofitInterface::class.java)
        setAddressRetrofitInterface.searchMyLocation(keyword).enqueue(object : Callback<SearchAddressResponse>{
            override fun onResponse(
                call: Call<SearchAddressResponse>,
                response: Response<SearchAddressResponse>
            ) {
                response.body()?.result.let {
                    if (it != null) {
                        view.onPostAddressSearchSuccess(it)
                    }
                }
            }

            override fun onFailure(call: Call<SearchAddressResponse>, t: Throwable) {
                t.message?.let { view.onPostAddressSearchFailure(it) }
            }

        })
    }


    fun tryGetAddressByMyLocation(postSetMyAddressRequest: PostSetMyAddressRequest){
        val setAddressRetrofitInterface = ApplicationClass.sRetrofit.create(
            SetAddressRetrofitInterface::class.java)
        setAddressRetrofitInterface.setMyLocation(postSetMyAddressRequest).enqueue(object :Callback<SetAddressResponse>{
            override fun onResponse(
                call: Call<SetAddressResponse>,
                response: Response<SetAddressResponse>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<SetAddressResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}
