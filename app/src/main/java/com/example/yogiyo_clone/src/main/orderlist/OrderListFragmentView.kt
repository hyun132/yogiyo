package com.example.yogiyo_clone.src.main.orderlist

import com.example.yogiyo_clone.src.main.orderlist.models.history.HistoryResponse

interface OrderListFragmentView {

    fun onGetOrderListSuccess(response: HistoryResponse)

    fun onGetOrderListFailure(message: String)
}