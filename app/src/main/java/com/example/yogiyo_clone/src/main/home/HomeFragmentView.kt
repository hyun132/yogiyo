package com.example.yogiyo_clone.src.main.home

import com.example.yogiyo_clone.src.main.home.models.HomeResponse

interface HomeFragmentView {

    fun onGetHomeInfoSuccess(response: HomeResponse)

    fun onGetHomeInfoFailure(message: String)

//    fun onPostSignUpSuccess(response: SignUpResponse)
//
//    fun onPostSignUpFailure(message: String)
}