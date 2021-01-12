package com.example.yogiyo_clone.util

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.src.main.home.models.Category
import com.example.yogiyo_clone.src.main.home.models.Store
import com.example.yogiyo_clone.src.order.OrderActivity

class ThemeHorizontalAdapter(array:List<Store>):RecyclerView.Adapter<ThemeHorizontalAdapter.CategoryViewHolder>() {

    var dataArray = array

    inner class CategoryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        @SuppressLint("SetTextI18n")
        fun bind(item:Store,position: Int){
            itemView.findViewById<TextView>(R.id.restaurant_name_textview).text=item.title
            itemView.findViewById<TextView>(R.id.review_grade_textview).text="${item.rateAvg}"
            itemView.findViewById<TextView>(R.id.review_number_textview).text="리뷰 ${item.countReview}"
            itemView.findViewById<TextView>(R.id.inform_textview).text=item.deliveryTime.split('~')[0]
            Glide.with(itemView.context).load(item.src).centerCrop().into(itemView.findViewById(R.id.restaurant_imageview))


            itemView.setOnClickListener {
                val intent = Intent(itemView.context, OrderActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.putExtra("storeId",item.storeIdx)
                itemView.context.startActivity(intent)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        var inflater =LayoutInflater.from(parent.context).inflate(R.layout.vertical_restaurant_item,parent,false)
        return CategoryViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(dataArray[position],position)
    }

    override fun getItemCount(): Int {
        return dataArray.size
    }



//    //클릭 인터페이스 정의
//    interface ItemClickListener {
//        fun onClick(view: View, position: Int)
//    }
//
//    //클릭리스너 선언
//    private lateinit var itemClickListner: ItemClickListener
//
//    //클릭리스너 등록 매소드
//    fun setItemClickListener(itemClickListener: ItemClickListener) {
//        this.itemClickListner = itemClickListener
//    }
}