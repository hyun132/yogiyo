package com.example.yogiyo_clone.src.order.menu.info

import com.example.yogiyo_clone.src.order.menu.info.models.BottomInfoResponse
import com.example.yogiyo_clone.src.order.menu.menuheader.model.MenuHeaderResponse

interface RestaurnatInfoFragmentView {
    fun onGetRestaurantInfoSuccess(response: BottomInfoResponse)

    fun onGetRestaurantInfoFailure(message: String)
}