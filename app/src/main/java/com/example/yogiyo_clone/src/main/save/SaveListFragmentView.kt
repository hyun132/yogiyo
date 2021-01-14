package com.example.yogiyo_clone.src.main.save

import com.example.yogiyo_clone.src.main.save.models.SaveListResponse

interface SaveListFragmentView {

    fun onGetSaveListSuccess(listResponse: SaveListResponse)

    fun onGetSaveListFailure(message: String)
}