package com.example.yogiyo_clone.src.login.signupinfo

import com.example.yogiyo_clone.src.login.signupinfo.model.PostUserValicationResponse

interface SignUpInfoFragmentView {
    fun onPostUserValidationSuccess(response: PostUserValicationResponse)

    fun onPostUserValidationFailure(message: String)
}