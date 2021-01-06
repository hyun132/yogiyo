package com.example.yogiyo_clone.src.searchaddress.textSearch.model


data class SearchAddressResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<AddressResult>
)