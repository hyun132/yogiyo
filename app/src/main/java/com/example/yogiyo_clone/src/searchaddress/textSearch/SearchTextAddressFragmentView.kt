package com.example.yogiyo_clone.src.searchaddress.textSearch

import com.example.yogiyo_clone.src.login.login.model.LogInResult
import com.example.yogiyo_clone.src.searchaddress.textSearch.model.AddressResult
import com.example.yogiyo_clone.src.searchaddress.textSearch.model.SearchAddressResponse

interface SearchTextAddressFragmentView {

    fun onPostAddressSearchSuccess(addressResults:List<AddressResult>)

    fun onPostAddressSearchFailure(message: String)

}