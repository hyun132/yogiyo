package com.example.yogiyo_clone.src.order.orderfood

import com.example.yogiyo_clone.src.order.orderfood.models.OrderResponse

interface OrderFragmentView {
    fun onPostOrderSuccess(response: OrderResponse)

    fun onPostOrderFailure(message: String)
}