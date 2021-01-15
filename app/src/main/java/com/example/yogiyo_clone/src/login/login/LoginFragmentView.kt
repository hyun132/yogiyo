package com.example.yogiyo_clone.src.login.login

import com.example.yogiyo_clone.src.login.login.model.LogInResponse
import com.example.yogiyo_clone.src.login.login.model.LogInResult

interface LoginFragmentView {

    fun onPostLogInSuccess(logInResult: LogInResponse)

    fun onPostLogInFailure(message: String)

//    fun onPostSignUpSuccess(response: SignUpResponse)
//
//    fun onPostSignUpFailure(message: String)
}