package com.example.yogiyo_clone.src.order.menu.review

import com.example.yogiyo_clone.src.order.menu.review.models.ReviewResponse
import retrofit2.Call
import retrofit2.http.*

interface BottomReviewRetrofitInterface {

    @GET("/stores/{idx}/reviews")
    fun getBottomReview(
        @Path("idx") idx: Int,
        @Query("sortId")sortId:Int
    ) : Call<ReviewResponse>

}
