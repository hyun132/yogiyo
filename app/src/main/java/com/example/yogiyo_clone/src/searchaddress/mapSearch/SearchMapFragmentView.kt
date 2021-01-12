package com.example.yogiyo_clone.src.searchaddress.mapSearch

import androidx.lifecycle.MutableLiveData
import com.example.yogiyo_clone.src.login.login.model.LogInResult
import com.example.yogiyo_clone.src.searchaddress.mapSearch.model.PostSetMyAddressRequest
import com.example.yogiyo_clone.src.searchaddress.mapSearch.model.SetAddressResponse
import com.example.yogiyo_clone.src.searchaddress.textSearch.model.SearchAddressResponse

interface SearchMapFragmentView {

    //여기는 어떻게 될지 좀 봐야할듯 내가 주소 보내야하는지..?
    fun onPostSetMapAddressSuccess(setAddressResponse: SetAddressResponse)

    fun onPostSetMapAddressFailure(message: String)

}