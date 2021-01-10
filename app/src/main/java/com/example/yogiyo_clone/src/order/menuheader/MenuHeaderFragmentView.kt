package com.example.yogiyo_clone.src.order.menuheader

import com.example.yogiyo_clone.src.login.signupinfo.model.PostUserValicationResponse
import com.example.yogiyo_clone.src.order.menuheader.model.MenuHeaderResponse

interface MenuHeaderFragmentView {
    fun onGetMenuHeaderSuccess(response: MenuHeaderResponse)

    fun onGetMenuHeaderFailure(message: String)
}