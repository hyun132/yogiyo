package com.example.yogiyo_clone.src.main.home

import com.example.yogiyo_clone.src.main.home.models.HomeResponse
import com.example.yogiyo_clone.src.main.home.models.bottom.BottomRecommandResponse

interface HomeFragmentView {

    fun onGetHomeInfoSuccess(response: HomeResponse)

    fun onGetHomeInfoFailure(message: String)

    fun onGetBottomRecommadSuccess(response: BottomRecommandResponse)

    fun onGetBottomRecommadFailure(message: String)
}