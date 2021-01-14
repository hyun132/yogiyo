package com.example.yogiyo_clone.src.order.menu.menubottom

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.src.order.OrderActivity
import com.example.yogiyo_clone.src.order.menu.menubottom.models.Menu
import com.example.yogiyo_clone.src.order.menu.menudetail.MenuDetailFragment
import com.example.yogiyo_clone.util.HorizentalFragment


class ExpandableListAdapter(data: MutableList<ExpandableListAdapter.Item>,_storeidx:Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    private val data: MutableList<ExpandableListAdapter.Item>

    val storeidx:Int = _storeidx

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): RecyclerView.ViewHolder {
        var view: View? = null
        val context: Context = parent.context
        val dp: Float = context.getResources().getDisplayMetrics().density
        val subItemPaddingLeft = (18 * dp).toInt()
        val subItemPaddingTopAndBottom = (5 * dp).toInt()
        when (type) {
            HEADER -> {
                val inflater =
                    parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                view = inflater.inflate(R.layout.expandable_item_header, parent, false)
                return ListHeaderViewHolder(view)
            }
            else -> {
//                val itemTextView = TextView(context)
//                itemTextView.setPadding(
//                    subItemPaddingLeft,
//                    subItemPaddingTopAndBottom,
//                    0,
//                    subItemPaddingTopAndBottom
//                )
//                itemTextView.setTextColor(-0x78000000)
//                itemTextView.layoutParams = ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT
//                )
                val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val itemTextView = inflater.inflate(R.layout.child_item, parent, false)
                return ChildHolder(itemTextView)
            }
        }
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item: ExpandableListAdapter.Item = data[position]
        when (item.type) {
            HEADER -> {
                val itemController: ListHeaderViewHolder? =
                    holder as ListHeaderViewHolder?
                if (itemController != null) {
                    itemController.refferalItem = item

                    itemController.header_title.setText(item.groupName)
                    if (item.invisibleChildren == null) {
                        itemController.btn_expand_toggle.setImageResource(R.drawable.ic_expanded)
                    } else {
                        itemController.btn_expand_toggle.setImageResource(R.drawable.ic_expandable)
                    }
                    itemController.btn_expand_toggle.setOnClickListener(View.OnClickListener {
                        if (item.invisibleChildren == null) {
                            item.invisibleChildren = ArrayList<ExpandableListAdapter.Item>()
                            var count = 0
                            val pos = data.indexOf(itemController?.refferalItem)
                            while (data.size > pos + 1 && data[pos + 1].type == ExpandableListAdapter.CHILD) {
                                (item.invisibleChildren)?.add(data.removeAt(pos + 1))
                                count++
                            }
                            notifyItemRangeRemoved(pos + 1, count)
                            itemController.btn_expand_toggle.setImageResource(R.drawable.ic_expandable)
                        } else {
                            val pos = data.indexOf(itemController.refferalItem)
                            var index = pos + 1
                            for (i in item.invisibleChildren!!) {
                                data.add(index, i)
                                index++
                            }
                            notifyItemRangeInserted(pos + 1, index - pos - 1)
                            itemController.btn_expand_toggle.setImageResource(R.drawable.ic_expanded)
                            item.invisibleChildren = null
                        }
                    })
                }
            }
            else -> {
//                val itemTextView = holder!!.itemView as
//                itemTextView.setText(data[position].groupName)
                val itemController: ChildHolder? = holder as ChildHolder?
                if (itemController != null) {
                    item.menu?.let { itemController.bind(it,storeidx) }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].type
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private class ListHeaderViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var header_title: TextView
        var btn_expand_toggle: ImageView
        var refferalItem: ExpandableListAdapter.Item? = null

        init {
            header_title = itemView.findViewById<View>(R.id.header_title) as TextView
            btn_expand_toggle = itemView.findViewById<View>(R.id.btn_expand_toggle) as ImageView
        }
    }

    private class ChildHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        fun bind(item: Menu,storeidx:Int){
            itemView.findViewById<TextView>(R.id.yogiseo_discount).visibility=View.GONE
            Glide.with(itemView).load(item.src).into(itemView.findViewById<ImageView>(R.id.menu_imageview))
            itemView.findViewById<TextView>(R.id.menu_name_textview).text=item.title
            itemView.findViewById<TextView>(R.id.menu_description_textview).text=item.description
            itemView.findViewById<TextView>(R.id.menu_price_textview).text=item.price.toString()


            itemView.setOnClickListener {
                val newFragment = MenuDetailFragment()
                newFragment.arguments= Bundle().apply {
                    putInt("menuIdx",item.menuIdx)
                    putInt("storeIdx",storeidx)
                    putString("menuName",item.title)
                }
                val activity = itemView.context as OrderActivity
                val transaction = activity.supportFragmentManager.beginTransaction().apply {

                    replace(R.id.order_frame, newFragment)
                    addToBackStack(null)
                }
                transaction.commit();
            }
        }
    }

    data class Item (
        var type: Int = 1,
        var groupName: String? = "",
        val groupNum: Int = 0,
        val menu: Menu?=null,
        var invisibleChildren: MutableList<Item>? = null
    )

    companion object {
        const val HEADER = 0
        const val CHILD = 1
    }

    init {
        this.data = data
    }


}