package com.example.yogiyo_clone.util

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.BaseFragment
import com.example.yogiyo_clone.databinding.FragmentHorizentalBinding
import com.example.yogiyo_clone.src.main.home.models.Store
import com.example.yogiyo_clone.src.main.home.models.Theme

class HorizentalFragment(theme: Theme) : BaseFragment<FragmentHorizentalBinding>(FragmentHorizentalBinding::bind,R.layout.fragment_horizental) {
    val data = theme

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val str= listOf<Store>(Store(20,10,2000,"30-40분",0,"FALSE","메뉴1, 메뉴2",5,"https://t1.daumcdn.net/cfile/tistory/994A334D5D138A8A03",0,"명동칼국수")
        ,Store(20,10,2000,"30-40분",0,"FALSE","메뉴1, 메뉴2",5,"https://t1.daumcdn.net/cfile/tistory/994A334D5D138A8A03",0,"명동칼국수")
        ,Store(20,10,2000,"30-40분",0,"FALSE","메뉴1, 메뉴2",5,"https://t1.daumcdn.net/cfile/tistory/994A334D5D138A8A03",0,"명동칼국수")
        ,Store(20,10,2000,"30-40분",0,"FALSE","메뉴1, 메뉴2",5,"https://t1.daumcdn.net/cfile/tistory/994A334D5D138A8A03",0,"명동칼국수")
        ,Store(20,10,2000,"30-40분",0,"FALSE","메뉴1, 메뉴2",5,"https://t1.daumcdn.net/cfile/tistory/994A334D5D138A8A03",0,"명동칼국수")
        )
        binding.horizontalTitle.text=data.themeName
        binding.horizontalRecyclerview.adapter=ThemeHorizontalAdapter(str)
//        binding.horizontalRecyclerview.adapter=ThemeHorizontalAdapter(data.stores)
        binding.horizontalRecyclerview.addItemDecoration(HorizontalRecyclerDecoration(16))
        binding.horizontalRecyclerview.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)


    }




}