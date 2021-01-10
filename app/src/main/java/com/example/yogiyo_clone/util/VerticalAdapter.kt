package com.example.yogiyo_clone.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.src.main.home.models.Store

class VerticalAdapter: RecyclerView.Adapter<VerticalAdapter.VerticalViewHolder>() {

//    private var savedMovieList= emptyList<MovieModel>()

    private val differCallback = object : DiffUtil.ItemCallback<Store>() {
        //둘이 같은 객체인지
        override fun areItemsTheSame(oldItem: Store, newItem: Store): Boolean {
            return oldItem.storeIdx == newItem.storeIdx // 나중에 id로 변경
        }

        //둘이 같은 아이템인지
        override fun areContentsTheSame(oldItem: Store, newItem: Store): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    class VerticalViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){

        fun bind(item : Store){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.vertical_restaurant_item,parent,false)
        return VerticalViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: VerticalViewHolder, position: Int) {
        val restaurant = differ.currentList[position]
        holder.bind(restaurant)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}