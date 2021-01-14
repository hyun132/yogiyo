package com.example.yogiyo_clone.util

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.src.main.category.models.Store
import com.example.yogiyo_clone.src.order.OrderActivity


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

    inner class VerticalViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){

        fun bind(item: Store){
            Glide.with(itemView).load(item.icon).into(itemView.findViewById<ImageView>(R.id.restaurant_imageview))
            itemView.findViewById<TextView>(R.id.restaurant_name_textview).text=item.title
            itemView.findViewById<TextView>(R.id.review_grade_textview).text=item.rateAvg.toString()
            itemView.findViewById<TextView>(R.id.review_number_textview).text="리뷰 ${item.countReview}"
            itemView.findViewById<TextView>(R.id.represent_discount_textview).text="${item.discountCharge}원 할인"
            if (item.deliveryCharge!=null){
                itemView.findViewById<TextView>(R.id.deliver_fee).text="배달요금 ${item.deliveryCharge}원"
            }else itemView.findViewById<TextView>(R.id.deliver_fee).visibility=View.GONE
            itemView.findViewById<TextView>(R.id.represent_deliver_time).text=item.deliveryTime

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, OrderActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.putExtra("storeId",item.storeIdx)
                itemView.context.startActivity(intent)
            }
        }
        
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(
            R.layout.row_restaurant_item,
            parent,
            false
        )
        return VerticalViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: VerticalViewHolder, position: Int) {
        val restaurant = differ.currentList[position]
        holder.bind(restaurant)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
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

//private fun View.setOnTouchListener(onClickListener: View.OnClickListener) {
//
//}
