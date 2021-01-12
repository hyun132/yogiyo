package com.example.yogiyo_clone.src.order.menu.review

import com.example.yogiyo_clone.config.ApplicationClass
import com.example.yogiyo_clone.src.order.menu.review.models.ReviewResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetBottomReviewService(val view: BottomReviewFragment) { // 얘는 이전에 repository랑 비슷한 역할

    fun tryGetBottomReview(idx:Int,sortId:Int){
        val getBottomRetrofitInterface = ApplicationClass.sRetrofit.create(
            BottomReviewRetrofitInterface::class.java)
        getBottomRetrofitInterface.getBottomReview(idx,sortId).enqueue(object : Callback<ReviewResponse>{
            override fun onResponse(
                call: Call<ReviewResponse>,
                response: Response<ReviewResponse>
            ) {
                response.body()?.let { view.onGetBottomReviewSuccess(it) }
            }

            override fun onFailure(call: Call<ReviewResponse>, t: Throwable) {
                t.message?.let { it -> view.onGetBottomReviewFailure(it) }
            }


        })
    }

}
