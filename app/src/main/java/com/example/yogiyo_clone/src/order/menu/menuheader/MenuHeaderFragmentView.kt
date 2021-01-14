package com.example.yogiyo_clone.src.order.menu.menuheader

import com.example.yogiyo_clone.src.order.menu.menuheader.model.JjimResponse
import com.example.yogiyo_clone.src.order.menu.menuheader.model.MenuHeaderResponse

interface MenuHeaderFragmentView {
    fun onGetMenuHeaderSuccess(response: MenuHeaderResponse)

    fun onGetMenuHeaderFailure(message: String)

    fun onPostJjimSuccess(response: JjimResponse)

    fun onPostJjimFailure(message: String)
}