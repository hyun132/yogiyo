package com.example.yogiyo_clone.src.order.menu.menudetail

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.ApplicationClass.Companion.addComma
import com.example.yogiyo_clone.src.order.menu.ShoppingOptionItem
import com.example.yogiyo_clone.src.order.menu.menudetail.models.Option
import kotlin.math.log

class MenuOptionAdapter(_fragment: MenuDetailFragment) :RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val TYPE_HEADER = 0
    private val TYPE_ITEM = 1

    var fragment= _fragment
//    lateinit var myInterface : ItemClickInterfaceName
//    fun setOnItemSelected(yourIntrface: ItemClickInterfaceName) {
//        myInterface = yourIntrface
//    }

    var options:MutableList<Option>?=null
    var tempShoppingList:MutableList<ShoppingOptionItem> = mutableListOf()



    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private fun ViewGroup.inflate(layoutRes: Int): View = LayoutInflater.from(context).inflate(
        layoutRes,
        this,
        false
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> HeaderViewHolder(parent.inflate(R.layout.menu_option_title_item))
            else -> TaskViewHolder(parent.inflate(R.layout.menu_option_item))
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> TYPE_HEADER
            else -> TYPE_ITEM }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Holder에 따른 Binding 처리
        when (holder) {
            is HeaderViewHolder -> {
                holder.itemView.findViewById<TextView>(R.id.menu_option_title_textview).text =
                    options?.get(position)?.title
            }
            else -> {
                var item = options?.get(position - 1) // 내부 데이터를 사용하여 각 아이템 값 설정
                holder.itemView.apply {
                    holder.itemView.findViewById<TextView>(R.id.menu_option_price).text =
                        item?.price?.let { addComma(it)+"원" }
                    holder.itemView.findViewById<TextView>(R.id.menu_option_name).text =
                        item?.title.toString()
                    Glide.with(this).load(R.drawable.unchecked)
                        .into(holder.itemView.findViewById<ImageView>(R.id.checkbox))
                }
                holder.itemView.setOnClickListener {

//                    var fragment=MenuDetailFragment()
//                    fragment.shoppingItem
                    val optionItem = item?.let { it1 -> ShoppingOptionItem(it1.optionIdx,item.title,item.price) }
                    if (optionItem != null) {
                        if (optionItem in tempShoppingList){
                            tempShoppingList.remove(optionItem)
                            Glide.with(it).load(R.drawable.unchecked)
                                .into(holder.itemView.findViewById<ImageView>(R.id.checkbox))
                            if (item != null) {
                                fragment.setPrice(-item.price)
                            }
                        }else {
                            tempShoppingList.add(optionItem)
                            Glide.with(it).load(R.drawable.checked)
                                .into(holder.itemView.findViewById<ImageView>(R.id.checkbox))

                            if (item != null) {
                                fragment.setPrice(item.price)
                            }
                        }
                    }
//                    myInterface.onItemSelected(tempShoppingList)
                    fragment.shoppingItem.options =tempShoppingList
                    Log.d("currentOptions: ", fragment.shoppingItem.options.toString())
                    Log.d("currentOptions: ", fragment.shoppingItem.options.toString())

                }
            }
        }
    }

    override fun getItemCount(): Int {
        return (options?.size ?: -1) + 1
    }

}