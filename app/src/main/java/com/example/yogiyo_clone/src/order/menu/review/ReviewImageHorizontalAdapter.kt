package com.example.yogiyo_clone.src.order.menu.review

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.src.order.menu.review.models.Photo

class ReviewImageHorizontalAdapter(array:List<Photo>):RecyclerView.Adapter<ReviewImageHorizontalAdapter.CategoryViewHolder>() {

    var dataArray = array

    inner class CategoryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        @SuppressLint("SetTextI18n")
        fun bind(item:Photo,position: Int){
//            itemView.findViewById<TextView>(R.id.restaurant_name_textview).text=item.title
            Glide.with(itemView.context).load(item.src).centerCrop().into(itemView.findViewById(R.id.restaurant_imageview))

//
//            itemView.setOnClickListener {
//                val intent = Intent(itemView.context, OrderActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                intent.putExtra("storeId",item.storeIdx)
//                itemView.context.startActivity(intent)
//            }

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