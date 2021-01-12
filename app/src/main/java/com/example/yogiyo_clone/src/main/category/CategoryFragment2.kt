package com.example.yogiyo_clone.src.main.category

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.BaseFragment
import com.example.yogiyo_clone.databinding.FragmentCategoryBinding
import com.example.yogiyo_clone.src.main.category.models.CategoryRequest
import com.example.yogiyo_clone.src.main.category.models.CategoryResult
import com.example.yogiyo_clone.util.VerticalAdapter


class CategoryFragment2 : BaseFragment<FragmentCategoryBinding>(FragmentCategoryBinding::bind,R.layout.fragment_category) ,
CategoryFragmentView{

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryIdx= arguments?.getInt("category")
        val request = categoryIdx?.let { CategoryRequest(it, false, 0, 1) }
        if (request != null) {
//            CategoryService(this).tryGetCategoryInfo(request)
        }
    }

    override fun onCategoryInfoSuccess(result: CategoryResult) {
        val storeList=result.stores
        val adapter=VerticalAdapter()
        adapter.differ.submitList(storeList)
        binding.yogiyoRecyclerview.adapter=adapter
        binding.yogiyoRecyclerview.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
    }

    override fun onCategoryInfoFailure(message: String) {
        TODO("Not yet implemented")
    }

}

