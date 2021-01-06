package com.example.yogiyo_clone.src.login.phoneconfirm

import com.example.yogiyo_clone.src.login.phoneconfirm.model.PhoneAuthResponse
import com.example.yogiyo_clone.src.login.phoneconfirm.model.SignUpResponse

interface PhoneAuthFragmentView {

    fun onPostSendSmsSuccess(phoneAuthResponse: PhoneAuthResponse)

    fun onPostSendSmsFailure(message: String)

    fun onPostSignUpSuccess(signUpResponse: SignUpResponse)

    fun onPostSignUpFailure(message: String)
}