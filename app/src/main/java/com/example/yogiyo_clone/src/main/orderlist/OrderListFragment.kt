package com.example.yogiyo_clone.src.main.orderlist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.BaseFragment
import com.example.yogiyo_clone.databinding.FragmentOrderListBinding
import com.example.yogiyo_clone.src.main.orderlist.models.history.HistoryResponse
import com.example.yogiyo_clone.src.main.orderlist.models.history.Order
import com.example.yogiyo_clone.src.order.OrderActivity
import com.google.android.material.tabs.TabLayout


class OrderListFragment : BaseFragment<FragmentOrderListBinding>(
    FragmentOrderListBinding::bind,
    R.layout.fragment_order_list
),
    OrderListFragmentView {

    var type = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.orderlistTablayout.addTab(binding.orderlistTablayout.newTab().setText("터치주문 0"))
        binding.orderlistTablayout.addTab(binding.orderlistTablayout.newTab().setText("전화주문 0"))
        binding.orderlistTablayout.addTab(binding.orderlistTablayout.newTab().setText("요마트주문 0 "))

        binding.orderlistTablayout.setScrollX(binding.orderlistTablayout.getWidth());
        binding.orderlistTablayout.getTabAt(type)?.select()

//        OrderListService(this).tryGetOrderList(type)

        binding.orderlistTablayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    OrderListService(this@OrderListFragment).tryGetOrderList(tab.position)
                    type = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

    }

    override fun onResume() {
        super.onResume()

        OrderListService(this).tryGetOrderList(0)
    }

    override fun onGetOrderListSuccess(response: HistoryResponse) {

        Log.d("hostoryresponse : ", response.result?.orders?.size.toString())

        val adapter = response?.result?.orders?.let { ListViewAdapter(it) }
        binding.orderListListview.adapter = adapter

        binding.orderListListview.setOnItemClickListener(object : AdapterView.OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                val intent = Intent(requireContext(), OrderActivity::class.java)
                intent.putExtra("storeId", response.result?.orders?.get(position)?.storeIdx)
                requireActivity().startActivity(intent)
            }

        })


        binding.orderlistTablayout.getTabAt(type)
            ?.setText("터치주문 ${response.result?.types?.touch}")
        binding.orderlistTablayout.getTabAt(type)
            ?.setText("전화주문 ${response.result?.types?.phone}")
        binding.orderlistTablayout.getTabAt(type)
            ?.setText("요마트주문 ${response.result?.types?.yomart}")

//        if (respo)



    }

    override fun onGetOrderListFailure(message: String) {
        Log.d("hostoryresponse : ", "fail")
    }

}

class ListViewAdapter(private val items: List<Order>) : BaseAdapter() {
    override fun getCount(): Int = items.size
    override fun getItem(position: Int): Order = items[position]
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {

        var convertView = view
        if (convertView == null) convertView =
            LayoutInflater.from(parent?.context).inflate(R.layout.order_history_item, parent, false)
        val item: Order = items[position]

        convertView!!.findViewById<TextView>(R.id.restaurant_name_textview).text = item.storeTitle
        convertView.findViewById<TextView>(R.id.menu_name_textview).text = item.menu
        convertView.findViewById<TextView>(R.id.order_time_textview).text = item.date

        return convertView
    }
}


