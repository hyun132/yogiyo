package com.example.yogiyo_clone.src.main.search

import com.example.yogiyo_clone.src.main.save.models.SaveListResponse
import com.example.yogiyo_clone.src.main.search.models.menusearch.MenuSearchResponse
import com.example.yogiyo_clone.src.main.search.models.storesearch.StoreSearchResponse

interface SearchFragmentView {

    fun onGetStoreSearchSuccess(listResponse: StoreSearchResponse)

    fun onGetStoreSearchFailure(message: String)

    fun onGetMenuSearchSuccess(listResponse: MenuSearchResponse)

    fun onGetMenuSearchFailure(message: String)
}