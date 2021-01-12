package com.example.yogiyo_clone.src.order.menu.review

import com.example.yogiyo_clone.src.order.menu.review.models.ReviewResponse

interface BottomReviewFragmentView {
    fun onGetBottomReviewSuccess(response: ReviewResponse)

    fun onGetBottomReviewFailure(message: String)
}