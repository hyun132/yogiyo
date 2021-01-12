package com.example.yogiyo_clone.src.order.menu.review

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.src.order.menu.review.models.Entire


class ReviewVerticalAdapter: RecyclerView.Adapter<ReviewVerticalAdapter.VerticalViewHolder>() {

//    private var savedMovieList= emptyList<MovieModel>()

    private val differCallback = object : DiffUtil.ItemCallback<Entire>() {
        //둘이 같은 객체인지
        override fun areItemsTheSame(oldItem: Entire, newItem: Entire): Boolean {
            return oldItem.reviewIdx == newItem.reviewIdx // 나중에 id로 변경
        }

        //둘이 같은 아이템인지
        override fun areContentsTheSame(oldItem: Entire, newItem: Entire): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    inner class VerticalViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){

        @SuppressLint("SetTextI18n")
        fun bind(item: Entire){
//            Glide.with(itemView).load(item.icon).into(itemView.findViewById<ImageView>(R.id.user_level_imageview))
            Glide.with(itemView).load(item.src).into(itemView.findViewById<ImageView>(R.id.review_image))
            itemView.findViewById<TextView>(R.id.user_name).text=item.nickname
            itemView.findViewById<TextView>(R.id.user_date).text=item.date
            itemView.findViewById<RatingBar>(R.id.review_ratingbar).rating= item.totalRate?.toFloat()!!
            itemView.findViewById<TextView>(R.id.taste_review_score).text=item.tasteRate.toString()
            itemView.findViewById<TextView>(R.id.amount_review_score).text=item.tasteRate.toString()
            itemView.findViewById<TextView>(R.id.delivery_review_score).text=item.tasteRate.toString()
            itemView.findViewById<TextView>(R.id.review_content_textview).text=item.content
            itemView.findViewById<TextView>(R.id.chef_reply).text= item.ownerContent
            itemView.findViewById<TextView>(R.id.chef_reply_time).text=item.ownerDate.toString()

        }
        
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(
            R.layout.review_vertical_item,
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
