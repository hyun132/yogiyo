package com.example.yogiyo_clone.src.order.menu.menudetail

import com.example.yogiyo_clone.src.order.menu.info.models.BottomInfoResponse
import com.example.yogiyo_clone.src.order.menu.menudetail.models.MenuResponse
import com.example.yogiyo_clone.src.order.menu.menuheader.model.MenuHeaderResponse

interface MenuDetailFragmentView {
    fun onGetMenuDetailSuccess(response: MenuResponse)

    fun onGetMenuDetailFailure(message: String)
}