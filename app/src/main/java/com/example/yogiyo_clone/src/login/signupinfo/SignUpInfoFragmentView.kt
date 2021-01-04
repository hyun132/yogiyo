package com.example.yogiyo_clone.src.login.signupinfo

import com.example.yogiyo_clone.src.login.signupinfo.model.PostUserValicationResponse
import com.softsquared.template.kotlin.src.main.home.models.SignUpResponse

interface SignUpInfoFragmentView {
    fun onPostUserValidationSuccess(response: PostUserValicationResponse)

    fun onPostUserValidationFailure(message: String)
}