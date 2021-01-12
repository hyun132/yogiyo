package com.example.yogiyo_clone.src.order.menubottom

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.src.order.menubottom.models.Entire


class ExpandableListAdapter3() : RecyclerView.Adapter<ExpandableListAdapter3.ParaentViewHolder>() {

    inner class ParaentViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){

        @SuppressLint("SetTextI18n")
        fun bind(item: Entire){

            itemView.findViewById<TextView>(R.id.header_title).text=item.groupName

            itemView.setOnClickListener {
                Log.d("holder : ","clicked")
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParaentViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(
            R.layout.expandable_item_header,
            parent,
            false
        )
        return ParaentViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ParaentViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val differCallback = object : DiffUtil.ItemCallback<Entire>() {
        //둘이 같은 객체인지
        override fun areItemsTheSame(oldItem: Entire, newItem: Entire): Boolean {
            return oldItem.groupNum == newItem.groupNum // 나중에 id로 변경
        }

        //둘이 같은 아이템인지
        override fun areContentsTheSame(oldItem: Entire, newItem: Entire): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)


}
