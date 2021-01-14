package com.example.yogiyo_clone.src.main.orderlist

import com.example.yogiyo_clone.src.main.orderlist.models.history.HistoryResult

interface OrderListFragmentView {

    fun onGetOrderListSuccess(response: HistoryResult)

    fun onGetOrderListFailure(message: String)
}