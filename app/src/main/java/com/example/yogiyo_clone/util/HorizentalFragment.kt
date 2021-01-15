package com.example.yogiyo_clone.util

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.BaseFragment
import com.example.yogiyo_clone.databinding.FragmentHorizentalBinding
import com.example.yogiyo_clone.src.main.home.models.Theme

class HorizentalFragment : BaseFragment<FragmentHorizentalBinding>(FragmentHorizentalBinding::bind,R.layout.fragment_horizental) {
//    val data = theme

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var data:Theme? = null
        data = arguments?.getSerializable("theme") as Theme?

        if (data != null) {
            binding.horizontalTitle.text=data.themeName
        }
        val themeAdapter= data?.let { it.stores?.let { it1 -> ThemeHorizontalAdapter(it1) } }
//        themeAdapter.setItemClickListener(object :ThemeHorizontalAdapter.ItemClickListener{
//            override fun onClick(view: View, position: Int) {
//                Log.d("hello","clicked")
//            }
//
//        })
        binding.horizontalRecyclerview.adapter=themeAdapter
//        binding.horizontalRecyclerview.adapter=ThemeHorizontalAdapter(data.stores)
        binding.horizontalRecyclerview.addItemDecoration(HorizontalRecyclerDecoration(16))
        binding.horizontalRecyclerview.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)


    }




}