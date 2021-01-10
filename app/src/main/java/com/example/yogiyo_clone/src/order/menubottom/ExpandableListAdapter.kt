package com.example.yogiyo_clone.src.order.menubottom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yogiyo_clone.R


class ExpandableListAdapter(private val data: MutableList<Item?>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
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
            CHILD -> {
                val itemTextView = TextView(context)
                itemTextView.setPadding(
                    subItemPaddingLeft,
                    subItemPaddingTopAndBottom,
                    0,
                    subItemPaddingTopAndBottom
                )
                itemTextView.setTextColor(-0x78000000)
                itemTextView.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                return object : RecyclerView.ViewHolder(itemTextView) {}
            }
            else -> return ListHeaderViewHolder(view)
        }
    }


    override fun getItemViewType(position: Int): Int {
        return data[position]!!.type
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private class ListHeaderViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        lateinit var header_title: TextView
        lateinit var btn_expand_toggle: ImageView
        var refferalItem: Item? = null

        init {
            if (itemView != null) {
                header_title = itemView.findViewById(R.id.header_title)
                btn_expand_toggle = itemView.findViewById(R.id.btn_expand_toggle) as ImageView
            }

        }
    }

    class Item {
        var type = 0
        var text: String? = null
        var invisibleChildren: MutableList<Item?>? = null

        constructor() {}
        constructor(type: Int, text: String?) {
            this.type = type
            this.text = text
        }
    }

    companion object {
        const val HEADER = 0
        const val CHILD = 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]
        when (item!!.type) {
            HEADER -> {
                val itemController = holder as ListHeaderViewHolder?
                itemController!!.refferalItem = item
                itemController.header_title.text = item.text
                if (item.invisibleChildren == null) {
                    itemController.btn_expand_toggle.setImageResource(R.drawable.ic_expandable)
                } else {
                    itemController.btn_expand_toggle.setImageResource(R.drawable.ic_expanded)
                }
                itemController.btn_expand_toggle.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(v: View?) {
                        if (item.invisibleChildren == null) {
                            item.invisibleChildren = ArrayList()
                            var count = 0
                            val pos = data.indexOf(itemController.refferalItem)
                            while (data.size > pos + 1 && data[pos + 1]!!.type == CHILD) {
                                (item.invisibleChildren as ArrayList<Item?>).add(data.removeAt(pos + 1))
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
                    }
                })
            }
            CHILD -> {
                val itemTextView = holder!!.itemView as TextView
                itemTextView.text = data[position]!!.text
            }
        }
    }
}
