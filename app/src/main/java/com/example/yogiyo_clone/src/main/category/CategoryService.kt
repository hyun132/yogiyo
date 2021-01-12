package com.example.yogiyo_clone.src.main.category

import android.util.Log
import com.example.yogiyo_clone.config.ApplicationClass
import com.example.yogiyo_clone.src.main.category.models.CategoryRequest
import com.example.yogiyo_clone.src.main.category.models.CategoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryService(val view: CategoryFragment) { // 얘는 이전에 repository랑 비슷한 역할

    fun tryGetCategoryInfo(categoryRequest: CategoryRequest){
        val categoryRetrofitInterface = ApplicationClass.sRetrofit.create(CategoryRetrofitInterface::class.java)
        categoryRetrofitInterface.getCategoryInfo(categoryRequest.categoryId,categoryRequest.isExpress,categoryRequest.sortId,categoryRequest.pay_method)
            .enqueue(object : Callback<CategoryResponse>{
                override fun onResponse(
                    call: Call<CategoryResponse>,
                    response: Response<CategoryResponse>
                ) {
                    response.body()?.let { view.onCategoryInfoSuccess(it.result) }
                    Log.d("onResponse : ", "success")
                }

                override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                    t.message?.let { view.onCategoryInfoFailure(it) }
                }


            })
    }

}
