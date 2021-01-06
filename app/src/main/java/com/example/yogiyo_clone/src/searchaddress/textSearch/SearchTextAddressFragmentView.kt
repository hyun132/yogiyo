package com.example.yogiyo_clone.src.searchaddress.textSearch

import com.example.yogiyo_clone.src.login.login.model.LogInResult
import com.example.yogiyo_clone.src.searchaddress.textSearch.model.SearchAddressResponse

interface SearchTextAddressFragmentView {

    fun onPostAddressSearchSuccess(textSearchResult:SearchAddressResponse)

    fun onPostAddressSearchFailure(message: String)

}