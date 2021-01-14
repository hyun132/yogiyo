package com.example.yogiyo_clone.src.order.menu.menubottom

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.BaseFragment
import com.example.yogiyo_clone.databinding.FragmentBottomMenuBinding
import com.example.yogiyo_clone.src.order.menu.menubottom.models.BottomMenuResponse


class BottomMenuFragment : BaseFragment<FragmentBottomMenuBinding>(FragmentBottomMenuBinding::bind,
    R.layout.fragment_bottom_menu), BottomMenuFragmentView{

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { BottomMenuService(this).tryGetBottomMenu(it.getInt("storeidx")) }
//        arguments?.let { BottomMenuService(this).tryGetBottomMenu(1) }

    }

    override fun onGetBottomMenuSuccess(response: BottomMenuResponse) {

        var topData = response.result.tops
        var bottomData = response.result.entire



        val expandableRecyclerview = binding.menuExpandableRecyclerview
        val data = mutableListOf<ExpandableListAdapter.Item>()
        expandableRecyclerview.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        for (paraent in bottomData){
            data.add(ExpandableListAdapter.Item(type = 0, groupName = paraent.groupName,groupNum = paraent.groupNum,))
            for (child in paraent.menus){
                data.add(ExpandableListAdapter.Item(menu = child))
            }
        }
        expandableRecyclerview.adapter=ExpandableListAdapter(data,response.result.basic.storeIdx)
        expandableRecyclerview.addItemDecoration(DividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL))
    }

    override fun onGetBottomMenuFailure(message: String) {
    }

}