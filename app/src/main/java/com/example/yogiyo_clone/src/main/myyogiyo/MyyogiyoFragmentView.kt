package com.example.yogiyo_clone.src.main.myyogiyo

import com.softsquared.template.kotlin.src.main.home.models.UserResponse

interface MyyogiyoFragmentView {

    fun onGetUserInfoSuccess(response: UserResponse)

    fun onGetUserInfoFailure(message: String)
}