package com.example .yogiyo_clone.src.order.menu.menudetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.ApplicationClass.Companion.addComma
import com.example.yogiyo_clone.config.ApplicationClass.Companion.currentOrderList
import com.example.yogiyo_clone.config.ApplicationClass.Companion.currentStore
import com.example.yogiyo_clone.config.BaseFragment
import com.example.yogiyo_clone.databinding.FragmentMenuDetailBinding
import com.example.yogiyo_clone.src.order.menu.ShoppingCart
import com.example.yogiyo_clone.src.order.menu.ShoppingItem
import com.example.yogiyo_clone.src.order.menu.menudetail.models.MenuResponse
import com.example.yogiyo_clone.src.order.menu.menudetail.models.Option
import com.example.yogiyo_clone.src.order.orderfood.OrderFragment


class MenuDetailFragment : BaseFragment<FragmentMenuDetailBinding>(
    FragmentMenuDetailBinding::bind,
    R.layout.fragment_menu_detail
), MenuDetailFragmentView {

    var shoppingItem=ShoppingItem(menuIdx = 0,options = mutableListOf(),menuPrice = 0,menuName = "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var menuIdx = arguments?.getInt("menuIdx")
        var menuName = arguments?.getString("menuName")
        var storeIdx= arguments?.getInt("storeIdx")
        if (menuIdx != null) {
            MenuDetailService(this).tryGetMenuDetail(menuIdx)
            shoppingItem.menuIdx=menuIdx
            shoppingItem.menuName = menuName!!
        }


        binding.addOrderListButton.setOnClickListener {
            if (storeIdx != null) {
                saveOrderList(storeIdx)
            }
            //메뉴 이미 있으면 count+1
            //어

            onDestroy()
        }

        binding.orderNowButton.setOnClickListener {
            if (storeIdx != null) {
                saveOrderList(storeIdx)
            }
            val newFragment = OrderFragment()
//            newFragment.arguments=Bundle().apply {
//                putSerializable("shoppinglist",shoppingItem)
//            }
            val transaction = requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.order_frame, newFragment)
                addToBackStack(null)
            }
            transaction.commit();
        }

    }

    fun saveOrderList(storeIdx:Int){
        if(currentOrderList==null){
            shoppingItem.count+=1
            currentOrderList=
                storeIdx?.let { it1 -> ShoppingCart(it1, mutableListOf(shoppingItem),
                    currentStore!!.deliveryCharge, currentStore!!.discountCharge ,shoppingItem.menuPrice) }
        }else{
            if (currentOrderList!!.storeIdx == storeIdx){
                currentOrderList!!.menus.add(shoppingItem)
                currentOrderList!!.totalPrice+=shoppingItem.menuPrice
            }else
                currentOrderList=
                    storeIdx?.let { it1 -> ShoppingCart(it1, mutableListOf(shoppingItem),currentStore!!.deliveryCharge,
                        currentStore!!.discountCharge ,shoppingItem.menuPrice) }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onGetMenuDetailSuccess(response: MenuResponse) {
        val recycleridx = arrayOf(
            binding.optionRecyclerview1,
            binding.optionRecyclerview2,
            binding.optionRecyclerview3,
            binding.optionRecyclerview4,
            binding.optionRecyclerview5,
            binding.optionRecyclerview6
        )
        val adapter = MenuOptionAdapter(this)
        for ((index, optionGroup) in response.result.entireOption.withIndex()){
            val header = Option(optionGroup.groupNum, 0, optionGroup.groupName)
            var optionlist:MutableList<Option> = optionGroup.options as MutableList<Option>
            optionlist.add(0, header)
            adapter.options=optionlist
            recycleridx[index].visibility=View.VISIBLE
            recycleridx[index].adapter=adapter
            recycleridx[index].layoutManager=LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        }

        binding.menuNameTextview.text=response.result.menu.title
        binding.menuDescriptionTextview.text=response.result.menu.description
        binding.menuReview.text=response.result.menu.reviewCount.toString()
        Glide.with(this).load(response.result.menu.src).centerCrop().into(binding.menuImageview)
        binding.menuPriceTextview.text = addComma(response.result.menu.price)+"원"
        binding.totalPriceTextview.text = addComma(response.result.menu.price)+"원"
        shoppingItem.menuPrice = response.result.menu.price

    }

    override fun onGetMenuDetailFailure(message: String) {

    }

    @SuppressLint("SetTextI18n")
    fun setPrice(price:Int){
        shoppingItem.menuPrice = shoppingItem.menuPrice.plus(price)
        binding.totalPriceTextview.text= addComma(shoppingItem.menuPrice)+"원"
    }
}