package com.example.yogiyo_clone.src.main.category

import com.example.yogiyo_clone.src.main.category.models.CategoryResponse
import com.example.yogiyo_clone.src.main.category.models.CategoryResult
import com.example.yogiyo_clone.src.main.home.models.HomeResponse

interface CategoryFragmentView {

    fun onCategoryInfoSuccess(result: CategoryResult)

    fun onCategoryInfoFailure(message: String)

}