package com.example.yogiyo_clone.src.main.myyogiyo

import com.example.yogiyo_clone.src.main.myyogiyo.models.UserResponse

interface MyyogiyoFragmentView {

    fun onGetUserInfoSuccess(response: UserResponse)

    fun onGetUserInfoFailure(message: String)
}