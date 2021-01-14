package com.example.yogiyo_clone.src.order.orderfood

import android.Manifest.permission.*
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.ApplicationClass.Companion.addComma
import com.example.yogiyo_clone.config.ApplicationClass.Companion.currentOrderList
import com.example.yogiyo_clone.config.ApplicationClass.Companion.detailAddress
import com.example.yogiyo_clone.config.ApplicationClass.Companion.roadAddress
import com.example.yogiyo_clone.config.BaseFragment
import com.example.yogiyo_clone.databinding.FragmentOrderBinding
import com.example.yogiyo_clone.src.main.MainActivity
import com.example.yogiyo_clone.src.order.menu.ShoppingItem
import com.example.yogiyo_clone.src.order.orderfood.models.Order
import com.example.yogiyo_clone.src.order.orderfood.models.OrderRequest
import com.example.yogiyo_clone.src.order.orderfood.models.OrderResponse


class OrderFragment : BaseFragment<FragmentOrderBinding>(
    FragmentOrderBinding::bind,
    R.layout.fragment_order
),OrderFragmentView {

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val shoppinList: Serializable? = arguments?.getSerializable("shoppinglist")
        Log.d("getArgs : ", currentOrderList?.storeIdx.toString())
        val shoppinList = currentOrderList?.menus

        binding.dongAddressTextview.text=roadAddress.value.toString()
        binding.roadAddressTextview.text=roadAddress.value.toString()
        binding.detailAddressEdittext.setText(detailAddress)

        if (ActivityCompat.checkSelfPermission(requireContext(), READ_SMS) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(), READ_PHONE_NUMBERS
            ) ==
            PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(), READ_PHONE_STATE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val tMgr = requireActivity().getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val mPhoneNumber = tMgr.line1Number
            binding.phoneEdittext.setText(mPhoneNumber)
            return
        } else {
            requestPermission()
        }

        binding.totalPriceTextview.text= addComma(currentOrderList!!.totalPrice - currentOrderList!!.discount_price + currentOrderList!!.delivery_fee)+"원"

        //여기서1초결제
        binding.oneSecPay.setOnClickListener {
            binding.yogiseoPaySpanArea.visibility=View.GONE
            binding.spanOneSecPayArea.visibility=View.VISIBLE
            binding.directlyPayMethodArea.visibility=View.GONE
        }

        //여기서결제
        binding.yogiseoPay.setOnClickListener {
            binding.yogiseoPaySpanArea.visibility=View.VISIBLE
            binding.spanOneSecPayArea.visibility=View.GONE
            binding.directlyPayMethodArea.visibility=View.GONE
        }

        //현장결제
        binding.directlyPay.setOnClickListener {
            binding.yogiseoPaySpanArea.visibility=View.GONE
            binding.spanOneSecPayArea.visibility=View.GONE
            binding.directlyPayMethodArea.visibility=View.VISIBLE
        }

        binding.orderButtom.setOnClickListener {

            val orderlist:List<Order> = converto_shppinItem_to_Order(shoppinList!!)
            val request = OrderRequest(
                storeIdx = currentOrderList!!.storeIdx,
                type = 1,
                address = roadAddress.value.toString(),
                deliveryCharge = currentOrderList!!.delivery_fee,
                discountCharge = currentOrderList!!.discount_price,
                hasDisposable = 0,
                isAtDoor = 0,
                isSaveTel = 1,
                orderList = orderlist,
                originalCharge = currentOrderList!!.totalPrice,
                paymentSystemIdx = 32,
                receipt = "abcd-abcd-abcd",
                tel = binding.phoneEdittext.text.toString(),
                totalCharge = currentOrderList!!.totalPrice - currentOrderList!!.discount_price + currentOrderList!!.delivery_fee,
                userRequest = "벨은 누르지 말아주세요"
            )

            OrderService(this).tryGetMenuDetail(request)
        }

        //deep copy
        var tempList= mutableListOf<ShoppingItem>()
        if (shoppinList != null) {
            shoppinList.forEach { tempList.add(it.copy()) }
        }
        //count -1 -> 배달비  -2 -> 할인
        if (currentOrderList!!.delivery_fee!=0){
            tempList?.add(ShoppingItem(0, -1, "배달요금", null, currentOrderList!!.delivery_fee))
        }
        if (currentOrderList!!.discount_price!=0){
            tempList?.add(ShoppingItem(0, -2, "즉시할인", null, currentOrderList!!.delivery_fee))
        }

//        if (shoppinList != null) {
//            Log.d("shoppinList",shoppinList.size.toString())
//        }
//        Log.d("tempList", tempList.size.toString())

        val adapter = tempList?.let { ListViewAdapter(it) }
        adapter.notifyDataSetChanged()
        binding.orderList.adapter=adapter

        var totalHeight = 30
        for (i in 0 until adapter.getCount()) {
            val listItem: View = adapter.getView(i, null, binding.orderList)
            listItem.measure(0, 0)
            totalHeight += listItem.measuredHeight
        }
        val params: ViewGroup.LayoutParams = binding.orderList.getLayoutParams()
        params.height =
            totalHeight + binding.orderList.getDividerHeight() * (adapter.getCount() - 1)
        binding.orderList.setLayoutParams(params)
//        binding.orderList.layoutParams.height= 50 * tempList.size-1+3
    }

    fun converto_shppinItem_to_Order(list: List<ShoppingItem>):List<Order>{
        val result= mutableListOf<Order>()

        for (item in list){
            var count = item.count
            var menuIdx = item.menuIdx
            var options =""
            for (idx in item.options!!) {
                options+=(idx.optionIdx.toString() + ",")
            }
            result.add(Order(count, menuIdx, options))
        }
        return result
    }

    fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(READ_SMS, READ_PHONE_NUMBERS, READ_PHONE_STATE), 100);
        }
    }

    override fun onPostOrderSuccess(response: OrderResponse) {
        Log.d("onPostOrderSuccess", "주문성공")

        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    override fun onPostOrderFailure(message: String) {
        Log.d("onPostOrderSuccess", "주문시ㄹ패")
    }


    class ListViewAdapter(private val items: MutableList<ShoppingItem>): BaseAdapter() {
        override fun getCount(): Int = items.size
        override fun getItem(position: Int): ShoppingItem = items[position]
        override fun getItemId(position: Int): Long = position.toLong()
        @SuppressLint("SetTextI18n", "ResourceAsColor")
        override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
            this.notifyDataSetChanged()
            var convertView = view
            if (convertView == null) convertView = LayoutInflater.from(parent?.context).inflate(
                R.layout.order_list_item,
                parent,
                false
            )
            val item: ShoppingItem = items[position]

            convertView!!.findViewById<TextView>(R.id.menu_name_textview).text="${item.menuName} x ${item.count}"
            if (item.menuIdx==-2){
                convertView.findViewById<TextView>(R.id.menu_price_textview).text= "-${addComma(item.menuPrice)}원"
                convertView.findViewById<TextView>(R.id.menu_price_textview).setTextColor(R.color.yellow)
            }else{
                convertView.findViewById<TextView>(R.id.menu_price_textview).text= "${addComma(item.menuPrice)}원"
            }

            var optionTxt=""
            if (item.options!=null){
                for (option in item.options!!){
                    optionTxt+="${option.optionName},"
                }
                if (optionTxt.last()==',') {optionTxt.dropLast(1)}
            }
            convertView.findViewById<TextView>(R.id.menu_option_name_textview).text=optionTxt
            return convertView
        }
    }


}