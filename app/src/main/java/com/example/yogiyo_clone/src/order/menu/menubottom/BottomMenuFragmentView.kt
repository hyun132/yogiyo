package com.example.yogiyo_clone.src.order.menu.menubottom

import com.example.yogiyo_clone.src.order.menu.menubottom.models.BottomMenuResponse

interface BottomMenuFragmentView {
    fun onGetBottomMenuSuccess(response: BottomMenuResponse)

    fun onGetBottomMenuFailure(message: String)
}