package com.example.yogiyo_clone.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.yogiyo_clone.R
import com.softsquared.template.kotlin.src.main.home.models.ResultRestaurant

class VerticalAdapter: RecyclerView.Adapter<VerticalAdapter.VerticalViewHolder>() {

//    private var savedMovieList= emptyList<MovieModel>()

    private val differCallback = object : DiffUtil.ItemCallback<ResultRestaurant>() {
        //둘이 같은 객체인지
        override fun areItemsTheSame(oldItem: ResultRestaurant, newItem: ResultRestaurant): Boolean {
            return oldItem.restaurant_name == newItem.restaurant_name // 나중에 id로 변경
        }

        //둘이 같은 아이템인지
        override fun areContentsTheSame(oldItem: ResultRestaurant, newItem: ResultRestaurant): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    class VerticalViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){

        fun bind(item : ResultRestaurant){

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