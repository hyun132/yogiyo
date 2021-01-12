package com.example.yogiyo_clone.src.order.menubottom

import com.example.yogiyo_clone.src.login.signupinfo.model.PostUserValicationResponse
import com.example.yogiyo_clone.src.order.menubottom.models.BottomMenuResponse
import com.example.yogiyo_clone.src.order.menuheader.model.MenuHeaderResponse

interface BottomMenuFragmentView {
    fun onGetBottomMenuSuccess(response: BottomMenuResponse)

    fun onGetBottomMenuFailure(message: String)
}