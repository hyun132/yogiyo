package com.example.yogiyo_clone.src.order.menuheader

import com.example.yogiyo_clone.src.login.signupinfo.model.PostUserValicationResponse

interface MenuHeaderFragmentView {
    fun onPostLoadMenuSuccess(response: PostUserValicationResponse)

    fun onPostLoadMenuFailure(message: String)
}